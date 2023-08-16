package com.rechail.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.*;
import java.security.MessageDigest;

import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import com.yangesoft.ylbdt.express.dto.AddressDto;
import com.yangesoft.ylbdt.express.dto.LogisticsInputDto;
import com.yangesoft.ylbdt.express.dto.LogisticsModeDto;
import com.yangesoft.ylbdt.express.dto.LogisticsProductDto;
import com.yangesoft.ylbdt.express.dto.LogisticsReturnDto;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CainiaoUtil {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		CainiaoUtil util = new CainiaoUtil();
		
		String dir = "C://";
		LogisticsInputDto order = util.getLogisticsInput();
		LogisticsReturnDto returnDto = util.addLogisticsInputDto(order, dir);
		String url ="https://prelink.cainiao.com/gateway/link.do";
		//传入参数
		Map<String,String> param =new HashMap<>();
		param.put("msg_type","CAINIAO_GLOBAL_HTCK_SERVICE_TAKING_ORDER");
		XmlBuilder xmlBuilder = new XmlBuilder();
		param.put("logistics_interface",xmlBuilder.createXml(order).toString());
		param.put("logistic_provider_id","2215963208416");
		String result = getInfo(url,param);
		System.out.println(result);

		//解析response获取mailNo,lpOrderCode
		byte[] bytes=new String(result).getBytes();     //String.getBytes()返回一 个字节数组。
		ByteArrayInputStream bin=new ByteArrayInputStream(bytes);
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document  document= builder.parse(bin);
		NodeList mailNoList=document.getElementsByTagName("mailNo");
		String mailNo=mailNoList.item(0).getTextContent();
		NodeList lpOrderCodeList=document.getElementsByTagName("lpOrderCode");
		String lpOrderCode=lpOrderCodeList.item(0).getTextContent();

		PdfUtil pdfUtil = new PdfUtil();
		pdfUtil.createPdfByTemplate(mailNo,lpOrderCode,order);






	}
	
	/**
	 * 
	 * @param order  订单对象
	 * @param dir    存放PDF文件目录
	 * @return
	 */
	public LogisticsReturnDto addLogisticsInputDto(LogisticsInputDto order, String dir) {
		LogisticsReturnDto returnDto = new LogisticsReturnDto();
		
		
		
		return returnDto;
	}
	
	public LogisticsInputDto getLogisticsInput(){
		LogisticsInputDto order = new LogisticsInputDto();
		LogisticsModeDto mode = new LogisticsModeDto();
		mode.setUserId("2215963208416");
		mode.setName("afllogisticslimited");
		order.setLogisticsMode(mode);
		//order
		order.setWaybill("AFLAI4663779749YQ");
		order.setOrderOutId("AFLAI4663779749YQ");
		//收件地址
		AddressDto cons = new AddressDto();
		cons.setCountryChineseName("香港特别行政区 ");
		cons.setAddress1("新界 葵青区 ");
		cons.setAddress2("青康路康泰楼630室香港九龍紅磡民裕街36號地下");
		cons.setCityCode("Slubice");
		cons.setCountryCode("HK");
		cons.setPostCode("47139");
		cons.setLinkman("Rolf Hansen");
		cons.setCompany("afl");
		cons.setTelephone("6513161123");
		cons.setEmail("12321adg@qq.com");
		order.setConsigneeAddress(cons);
		
		//发件地址
		AddressDto ship = new AddressDto();
		ship.setCountryChineseName("香港特别行政区 ");
		ship.setAddress1("香港岛 东区 ");
		ship.setAddress2("紅磡民裕街39號地下");
		ship.setCityCode("Ungenach");
		ship.setCountryCode("HK");
		ship.setPostCode("69-100");
		ship.setLinkman("AFL LOGISTICS LIMITED");
		ship.setCompany("AFL LOGISTICS LIMITED");
		ship.setTelephone("0048 608006293");
		order.setShipperAddress(ship);
		
		//产品
		List<LogisticsProductDto> products = new ArrayList<LogisticsProductDto>();
		LogisticsProductDto product = new LogisticsProductDto();
		product.setCnName("干红葡萄酒");
		product.setEnName("");
		product.setCount(1);
		product.setWeight(new BigDecimal(26000));
		product.setSellPrice(new BigDecimal(1100));
		product.setShelfCode("HK_LAND_LC");
		product.setWide(new BigDecimal(1));
		product.setHigh(new BigDecimal(1));
		product.setLength(new BigDecimal(1));
		products.add(product);
		order.setLogisticsProductDto(products);
		return order;
	}
	public static String doSign(String content, String charset, String keys) {
		String sign = "";
		content = content + keys;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(content.getBytes(charset));
			sign = new String(Base64.encodeBase64(md.digest()), charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sign;
	}

	public static String getInfo(String url, Map<String, String> param) throws IOException {
		String logistics_interface = param.get("logistics_interface");
		String msg_type = param.get("msg_type");
		String data_digest = doSign(logistics_interface,"UTF-8","e28F32G5H0G0hxp24G0l3k1p47oh1kW1");
		System.out.println(doSign(logistics_interface,"UTF-8","e28F32G5H0G0hxp24G0l3k1p47oh1kW1"));
		String logistic_provider_id =param.get("logistic_provider_id");

		OkHttpClient okHttpClient = new OkHttpClient();
		RequestBody body = new FormBody.Builder()
				.add("msg_type",msg_type)
				.add("logistics_interface",logistics_interface)
				.add("data_digest",data_digest)
				.add("logistic_provider_id",logistic_provider_id)
				.build();

		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try (Response response = okHttpClient.newCall(request).execute()) {
			return response.body().string();
		} catch (SocketTimeoutException e) {

			throw new RuntimeException("Okhttp timeout.");
		} catch (Exception e) {
			return null;
		}

	}


}
