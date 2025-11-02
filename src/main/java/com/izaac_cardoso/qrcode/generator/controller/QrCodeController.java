package com.izaac_cardoso.qrcode.generator.controller;

import com.google.zxing.WriterException;
import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.service.SampleService;
import com.izaac_cardoso.qrcode.generator.dtos.DTORequest;
import com.izaac_cardoso.qrcode.generator.domain.service.QrCodeService;

import org.springframework.http.HttpStatus;
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

    private final QrCodeService qrCodeService;
    private final SampleService sampleService;

    public QrCodeController(QrCodeService qrCodeService, SampleService sampleService) {
        this.qrCodeService = qrCodeService;
        this.sampleService = sampleService;
    }

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateBarCode(@RequestBody DTORequest request) throws WriterException {
        var qrCodeImage = qrCodeService.generateQRCode(request);

        return ResponseEntity.ok(qrCodeImage);
    }

    @PostMapping("/forms/submit")
    public ResponseEntity<?> submitData(@RequestBody Sample response) {
        sampleService.persist(response);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
