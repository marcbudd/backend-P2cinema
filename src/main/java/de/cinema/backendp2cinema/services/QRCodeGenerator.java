package de.cinema.backendp2cinema.services;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGenerator {

    public static byte[] generateQRCode(String text) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            text.trim();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 800, 800);

            ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();
            @SuppressWarnings("unused")
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0x000000, 0xFFFFFF);

            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteOutPutStream);
            byte[] byteData = byteOutPutStream.toByteArray();
            return byteData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
