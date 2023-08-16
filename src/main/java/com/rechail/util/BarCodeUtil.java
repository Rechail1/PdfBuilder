package com.rechail.util;


import com.google.zxing.BarcodeFormat;

import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BarCodeUtil {

    public static void main(String[] args) {
        BarCodeUtil.generateCode128("HK00153062482",200,32,"C:\\devtools\\barcode\\3.png");
    }
    public static void generateCode128(String data, int width, int height, String filePath) {
        try {
            Map<EncodeHintType,Object> hints = new HashMap<>();
            //设置条码两边空白边距为0，默认为10，如果宽度不是条码自动生成宽度的倍数则MARGIN无效
            hints.put(EncodeHintType.MARGIN, 0);
            width = new Code128Writer().encode(data).length;

            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, width, height,hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }
            ImageIO.write(image, "png", new File(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }




}
