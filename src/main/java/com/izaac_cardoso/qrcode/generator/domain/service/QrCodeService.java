package com.izaac_cardoso.qrcode.generator.domain.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.izaac_cardoso.qrcode.generator.dtos.DTORequest;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Service
public class QrCodeService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//  Implement a logic to filter what link should be delivered according to the id passed in
//    private String qRCodeFilter(String id, LocalDateTime date) {
//        Map<String, Supplier<String>> urls = Map.of(
//                "PB", () -> "http://domain.com/form?id=value&date=date",
//                "TPQM", () -> "http://domain.com/form?id=value&date=date",
//                "OUT", () -> "http://domain.com/form?id=value&date=date"
//        );
//
//        return urls.getOrDefault(id, () -> "Invalid id").get();
//    }

    private String qRCodeFilter(String id, LocalDateTime date) {
        Map<String, String> urls = Map.of(
                "PB","http://domain/form-1?id=s%&date=s%",
                "TPQM", "http://domain/form-2?id=s%&date=s%",
                "OUT", "http://domain/form-3?id=s%&date=s%"
        );

        String content = urls.get(id);
        if(content == null) {
            throw new IllegalArgumentException(id + " invalid");
        }

        return String.format(content, id, formatter.format(date));
    }

    public BufferedImage generateQRCode(DTORequest request) throws WriterException {
        if(request == null || request.id() == null || request.date() == null) {
            throw new IllegalArgumentException("Request, id and date must not be null.");
        }

        LocalDateTime formattedDate;
        try {
            formattedDate = LocalDateTime.parse(request.date(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be 'dd/MM/yyyy' format");
        }

        String content = qRCodeFilter(request.id(), formattedDate);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
