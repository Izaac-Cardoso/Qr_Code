package com.izaac_cardoso.qrcode.generator.controller;

import com.google.zxing.WriterException;
import com.izaac_cardoso.qrcode.generator.dtos.DTORequest;
import com.izaac_cardoso.qrcode.generator.domain.service.QrCodeService;

import com.izaac_cardoso.qrcode.generator.dtos.DTOResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("api/qrcode")
public class QrCodeController {

    private final QrCodeService service;

    public QrCodeController(QrCodeService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateBarCode(@RequestBody DTORequest request) throws WriterException {
        var qrCodeImage = service.generateQRCode(request);

        return ResponseEntity.ok(qrCodeImage);
    }

    @PostMapping("/forms/submit")
    public ResponseEntity<?> submitData(DTOResponse response) {

        return null;
    }
}
