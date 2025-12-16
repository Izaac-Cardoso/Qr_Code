package com.izaac_cardoso.qrcode.generator.controller;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @PostMapping("/forms/submit")
    public ResponseEntity<?> submitData(@RequestBody Sample newSample) {
        service.persist(newSample);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{date}")
    public ResponseEntity<?> getData(@PathVariable String id, @PathVariable LocalDate date) {
        return service.findSample(id, date);
    }

    @GetMapping("page/{id}")
    public ResponseEntity<List<Sample>> paginatedSamples(@PathVariable String id,
                                                         @RequestParam(required = false, defaultValue = "0") int pageNumber) {
        return service.getSampleById(id, pageNumber);
    }

    @GetMapping("/{init}/{end}")
    public ResponseEntity<List<Sample>> getSampleInInterval(@PathVariable LocalDate init, @PathVariable LocalDate end) {
        return service.findAllByCustomDate(init, end);
    }
}
