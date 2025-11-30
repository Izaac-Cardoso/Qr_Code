package com.izaac_cardoso.qrcode.generator.controller;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @PostMapping("/forms/submit")
    public ResponseEntity<?> submitData(@RequestBody Sample response) {
        service.persist(response);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Sample> getData(@RequestParam String sampleId, @RequestParam LocalDateTime date) {
        return null;
    }

}
