package com.rechail.util;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.yangesoft.ylbdt.express.dto.LogisticsInputDto;
import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfUtil {
    public static void main(String[] args) throws IOException {

    }

    public void createPdfByTemplate(String barcode, String lpOrderCode, LogisticsInputDto logisticsInputDto) throws IOException {
        PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simhei.ttf", PdfEncodings.IDENTITY_H);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader("C:\\devtools\\pdf\\template.PDF"),new PdfWriter("C:\\devtools\\pdf\\test1.pdf"));
        Document document = new Document(pdfDocument);
        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDocument,false);

        PdfFormField barCodeField = pdfAcroForm.getField("barcodeImg1");
        List<PdfWidgetAnnotation> pdfWidgetAnnotationList = barCodeField.getWidgets();
        PdfWidgetAnnotation widget = pdfWidgetAnnotationList.get(0);
        float x1 = widget.getRectangle().getAsNumber(0).floatValue();
        float y1 = widget.getRectangle().getAsNumber(1).floatValue();
        float x2 = widget.getRectangle().getAsNumber(2).floatValue();
        float y2 = widget.getRectangle().getAsNumber(3).floatValue();
        float fieldWidth = x2-x1;
        float fieldHeight =y2-y1;
        //生成条形码
        BarCodeUtil.generateCode128(barcode,200,32,"C:\\devtools\\barcode\\barcode.png");
        //旋转条形码
        Thumbnails.of("C:\\devtools\\barcode\\barcode.png")
                .size(134,32)
                .rotate(90)
                .toFile("C:\\devtools\\barcode\\barcodeRotate.png");
        Image image = new Image(ImageDataFactory.create("C:\\devtools\\barcode\\barcodeRotate.png"));
        image.scaleToFit(fieldWidth-5,fieldHeight-5);
        float scaleWidth = image.getImageScaledWidth();
        float scaleHeight = image.getImageScaledHeight();
        float countx1 =x1 + (fieldWidth/2) - (scaleWidth/2);
        float county1 =y1 + (fieldHeight/2) - (scaleHeight/2);
        image.setFixedPosition(countx1,county1);
        document.add(image);
        //barcode1
        barCodeField = pdfAcroForm.getField("barcode1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        Paragraph paragraph = new Paragraph(barcode).setFont(font).setFontSize(10).setRotationAngle(Math.PI/2);
        paragraph.setFixedPosition(x1+(fieldWidth/2),y1+(fieldHeight/2)-25f,fieldWidth*5);
        paragraph.setMarginLeft(-5);
        document.add(paragraph);

        //CN
        barCodeField = pdfAcroForm.getField("CN1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("CN").setBold().setFont(font).setFontSize(32).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);

        //宅配
        barCodeField = pdfAcroForm.getField("zhaipei");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("宅配").setFont(font).setFontSize(26).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);

        //address1
        barCodeField = pdfAcroForm.getField("address1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("AFLAI4663779749YQ").setFont(font).setFontSize(10).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //address2
        barCodeField = pdfAcroForm.getField("address2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("").setBold().setFont(font).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //address3
        barCodeField = pdfAcroForm.getField("address3");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("").setBold().setFont(font).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);

        //orderNo
        barCodeField = pdfAcroForm.getField("orderNo");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(lpOrderCode).setFont(font).setFontSize(10).setTextAlignment(TextAlignment.LEFT);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //receiverInfo1
        barCodeField = pdfAcroForm.getField("receiverInfo1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(logisticsInputDto.getConsigneeAddress().getLinkman()+"\n").setFont(font).setFontSize(10).setTextAlignment(TextAlignment.LEFT);
        paragraph.setMultipliedLeading(1f);
        paragraph.setFixedPosition(x1,y1+(fieldHeight)-20f,fieldWidth);
        paragraph.setHeight(fieldHeight);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        paragraph.add(logisticsInputDto.getConsigneeAddress().getTelephone()+"\n")
                .add(logisticsInputDto.getConsigneeAddress().getCountryChineseName()+logisticsInputDto.getConsigneeAddress().getAddress1()+logisticsInputDto.getConsigneeAddress().getAddress2()+"\n");
        document.add(paragraph);

        //receiver1
        barCodeField = pdfAcroForm.getField("receiver1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("签收人:                               签收时间:").setFont(font).setFontSize(5).setTextAlignment(TextAlignment.LEFT);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //fromInfo1
        barCodeField = pdfAcroForm.getField("fromInfo1");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(logisticsInputDto.getShipperAddress().getLinkman()+"\n").setFont(font).setFontSize(6).setTextAlignment(TextAlignment.LEFT);
        paragraph.setHeight(fieldHeight);
        paragraph.setMultipliedLeading(1f);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        paragraph.add(logisticsInputDto.getShipperAddress().getTelephone()+"\n")
                .add(logisticsInputDto.getShipperAddress().getCountryChineseName()+logisticsInputDto.getShipperAddress().getAddress1()+logisticsInputDto.getShipperAddress().getAddress2()+"\n");
        document.add(paragraph);

        //date
        Date day=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        barCodeField = pdfAcroForm.getField("date");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(sdf.format(day)).setFont(font).setFontSize(5).setTextAlignment(TextAlignment.RIGHT);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //cn2
        barCodeField = pdfAcroForm.getField("CN2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("CN").setBold().setFont(font).setFontSize(32).setTextAlignment(TextAlignment.CENTER);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        document.add(paragraph);
        //barcodeImg2
        barCodeField = pdfAcroForm.getField("barcodeImg2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        image = new Image(ImageDataFactory.create("C:\\devtools\\barcode\\barcode.png"));
        image.scaleToFit(fieldWidth-5,fieldHeight-5);
        scaleWidth = image.getImageScaledWidth();
        scaleHeight = image.getImageScaledHeight();
        countx1 =x1 + (fieldWidth/2) - (scaleWidth/2);
        county1 =y1 + (fieldHeight/2) - (scaleHeight/2);
        image.setFixedPosition(countx1,county1);
        document.add(image);
        //barcode2
        barCodeField = pdfAcroForm.getField("barcode2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(barcode).setFont(font).setFontSize(10).setTextAlignment(TextAlignment.LEFT);
        paragraph.setFixedPosition(x1+18f,y1+5f,fieldWidth);
        document.add(paragraph);
        //zipCode
        barCodeField = pdfAcroForm.getField("zipCode");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("728048").setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
        paragraph.setFixedPosition(x1,y1+5f,fieldWidth);
        document.add(paragraph);

        //receiverInfo2
        barCodeField = pdfAcroForm.getField("receiverInfo2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(logisticsInputDto.getConsigneeAddress().getLinkman()+"\n").setFont(font).setFontSize(8).setTextAlignment(TextAlignment.LEFT);
        paragraph.setFixedPosition(x1,y1+(fieldHeight)-16f,fieldWidth);
        paragraph.setMultipliedLeading(1f);
        paragraph.setHeight(fieldHeight);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        paragraph.add(logisticsInputDto.getConsigneeAddress().getTelephone()+"\n")
                .add(logisticsInputDto.getConsigneeAddress().getCountryChineseName()+logisticsInputDto.getConsigneeAddress().getAddress1()+logisticsInputDto.getConsigneeAddress().getAddress2()+"\n").setMarginTop(3f);
        document.add(paragraph);


        //receiver2

        barCodeField = pdfAcroForm.getField("receiver2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph("签收人:                               签收时间:").setFixedLeading(1.2f).setFont(font).setFontSize(5).setTextAlignment(TextAlignment.LEFT);
        paragraph.setFixedPosition(x1,y1+6f,fieldWidth);
        document.add(paragraph);
        //fromInfo2
        barCodeField = pdfAcroForm.getField("fromInfo2");
        pdfWidgetAnnotationList = barCodeField.getWidgets();
        widget = pdfWidgetAnnotationList.get(0);
        x1 = widget.getRectangle().getAsNumber(0).floatValue();
        y1 = widget.getRectangle().getAsNumber(1).floatValue();
        x2 = widget.getRectangle().getAsNumber(2).floatValue();
        y2 = widget.getRectangle().getAsNumber(3).floatValue();
        fieldWidth = x2-x1;
        fieldHeight =y2-y1;
        paragraph = new Paragraph(logisticsInputDto.getShipperAddress().getLinkman()+"\n").setFont(font).setFontSize(6).setTextAlignment(TextAlignment.LEFT);
        paragraph.setHeight(fieldHeight);
        paragraph.setMultipliedLeading(1f);
        paragraph.setFixedPosition(x1,y1,fieldWidth);
        paragraph.add(logisticsInputDto.getShipperAddress().getTelephone()+"\n")
                .add(logisticsInputDto.getShipperAddress().getCountryChineseName()+logisticsInputDto.getShipperAddress().getAddress1()+logisticsInputDto.getShipperAddress().getAddress2()+"\n").setMarginTop(3f);
        document.add(paragraph);

        document.add(paragraph);

        document.close();
    }



}
