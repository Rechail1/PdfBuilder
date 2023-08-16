package com.rechail.util;

import com.yangesoft.ylbdt.express.dto.LogisticsInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class XmlBuilder {

    private  StringBuilder xmlStringBuilder =new StringBuilder();

    public  StringBuilder createXml(LogisticsInputDto param){
        xmlStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlStringBuilder.append("<request>");
        xmlStringBuilder.append("    <customerParam>");
        xmlStringBuilder.append("        <userId>"+param.getLogisticsMode().getUserId()+"</userId>");
        xmlStringBuilder.append("        <nickName>"+param.getLogisticsMode().getName()+"</nickName>");
        xmlStringBuilder.append("    </customerParam>");
        xmlStringBuilder.append("    <orderParam>");
        xmlStringBuilder.append("        <outOrderCode>"+param.getOrderOutId()+"</outOrderCode>");
        xmlStringBuilder.append("        <productCode>"+param.getLogisticsProductDto().get(0).getShelfCode()+"</productCode>");
        xmlStringBuilder.append("        <senderParam>");
        xmlStringBuilder.append("            <name>"+param.getShipperAddress().getLinkman()+"</name>");
        xmlStringBuilder.append("            <mobile>"+param.getShipperAddress().getTelephone()+"</mobile>");
        xmlStringBuilder.append("            <company>"+param.getShipperAddress().getCompany()+"</company>");
        xmlStringBuilder.append("            <email>"+param.getShipperAddress().getEmail()+"</email>");
        xmlStringBuilder.append("            <zipCode>"+param.getShipperAddress().getPostCode()+"</zipCode>");
        xmlStringBuilder.append("            <countryCode>"+param.getShipperAddress().getCountryCode()+"</countryCode>");
        xmlStringBuilder.append("            <province>"+param.getShipperAddress().getCountryChineseName()+"</province>");
        xmlStringBuilder.append("            <city>"+param.getShipperAddress().getAddress1().split(" ")[0]+"</city>");
        xmlStringBuilder.append("            <district>"+param.getShipperAddress().getAddress1().split(" ")[1]+"</district>");
        xmlStringBuilder.append("            <detailAddress>"+param.getShipperAddress().getAddress2()+"</detailAddress>");
        xmlStringBuilder.append("            <intTelCode>null</intTelCode>");
        xmlStringBuilder.append("        </senderParam>");
        xmlStringBuilder.append("        <receiverParam>");
        xmlStringBuilder.append("            <name>"+param.getConsigneeAddress().getLinkman()+"</name>");
        xmlStringBuilder.append("            <mobile>"+param.getConsigneeAddress().getTelephone()+"</mobile>");
        xmlStringBuilder.append("            <company>"+param.getConsigneeAddress().getCompany()+"</company>");
        xmlStringBuilder.append("            <email>"+param.getConsigneeAddress().getEmail()+"</email>");
        xmlStringBuilder.append("            <zipCode>"+param.getConsigneeAddress().getPostCode()+"</zipCode>");
        xmlStringBuilder.append("            <countryCode>"+param.getConsigneeAddress().getCountryCode()+"</countryCode>");
        xmlStringBuilder.append("            <province>"+param.getConsigneeAddress().getCountryChineseName()+"</province>");
        xmlStringBuilder.append("            <city>"+param.getConsigneeAddress().getAddress1().split(" ")[0]+"</city>");
        xmlStringBuilder.append("            <district>"+param.getConsigneeAddress().getAddress1().split(" ")[1]+"</district>");
        xmlStringBuilder.append("            <detailAddress>"+param.getConsigneeAddress().getAddress2()+"</detailAddress>");
        xmlStringBuilder.append("            <intTelCode>null</intTelCode>");
        xmlStringBuilder.append("        </receiverParam>");
        xmlStringBuilder.append("        <pickUpParam>");
        xmlStringBuilder.append("            <code>DOOR_PICKUP</code>");
        xmlStringBuilder.append("        </pickUpParam>");
        xmlStringBuilder.append("        <deliveryParam>");
        xmlStringBuilder.append("            <code>DOOR_DELIVERY</code>");
        xmlStringBuilder.append("        </deliveryParam>");
        xmlStringBuilder.append("        <packageParams>");
        xmlStringBuilder.append("            <packageParam>");
        xmlStringBuilder.append("                <mailNo>"+param.getWaybill()+"</mailNo>");
        xmlStringBuilder.append("                <length>"+param.getLogisticsProductDto().get(0).getLength()+"</length>");
        xmlStringBuilder.append("                <width>"+param.getLogisticsProductDto().get(0).getWide()+"</width>");
        xmlStringBuilder.append("                <height>"+param.getLogisticsProductDto().get(0).getHigh()+"</height>");
        xmlStringBuilder.append("                <weight>"+param.getLogisticsProductDto().get(0).getWeight()+"</weight>");
        xmlStringBuilder.append("                <itemParams>");
        xmlStringBuilder.append("                    <itemParam>");
        xmlStringBuilder.append("                        <itemName>"+param.getLogisticsProductDto().get(0).getCnName()+"</itemName>");
        xmlStringBuilder.append("                        <quantity>"+param.getLogisticsProductDto().get(0).getCount()+"</quantity>");
        xmlStringBuilder.append("                        <unitPrice>"+param.getLogisticsProductDto().get(0).getSellPrice()+"</unitPrice>");
        xmlStringBuilder.append("                    </itemParam>");
        xmlStringBuilder.append("                </itemParams>");
        xmlStringBuilder.append("            </packageParam>");
        xmlStringBuilder.append("        </packageParams>");
        xmlStringBuilder.append("    </orderParam>");
        xmlStringBuilder.append("</request>");

    return xmlStringBuilder;
    }

}

