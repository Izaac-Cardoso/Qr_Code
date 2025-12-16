package com.izaac_cardoso.qrcode.generator.domain.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.net.URLEncoder;
import java.util.Base64;

@Service
public class QrCodeService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String qRCodeFilter(Sample request) {

        String encodedId = URLEncoder.encode(request.getId(), StandardCharsets.UTF_8);
        String encodedDate = URLEncoder.encode(request.getDate(), StandardCharsets.UTF_8);
        String encodedFields = Base64.getUrlEncoder().encodeToString(request.getFields().getBytes());

//      URIBuilder urlBuilder = new URIBuilder();
//	    urlBuilder.setHost("localhost");
//      urlBuilder.setPort(8080);
//      urlBuilder.addParameter("id=", encodedId);
//      urlBuilder.addParameter("fields=", encodedFields);
//	    URL url = urlBuilder.build().toURL();

        String url = "https://domain.com:8080/form-page?id=" + encodedId +
                "&date=" + encodedDate +
                "&fields=" + encodedFields;

        return url;
    }

    public BufferedImage generateQRCode(Sample request) throws WriterException {
        if(request.getId() == null || request.getDate() == null || request.getFields() == null) {
            throw new IllegalArgumentException("Id, date and fields must not be null.");
        }

        LocalDateTime formattedDate;
        try {
            formattedDate = LocalDateTime.parse(request.getDate(), formatter);
            request.setDate(LocalDate.parse(String.valueOf(formattedDate)));

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be 'dd/MM/yyyy' format");
        }

        String content = qRCodeFilter(request);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
