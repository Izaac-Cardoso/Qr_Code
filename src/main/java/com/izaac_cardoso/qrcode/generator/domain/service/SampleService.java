package com.izaac_cardoso.qrcode.generator.domain.service;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.exceptions.NotFoundException;
import com.izaac_cardoso.qrcode.generator.repository.CollectedSampleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SampleService {

    private final CollectedSampleRepository repository;

    public SampleService(CollectedSampleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void persist(Sample response) {
        repository.save(response);
    }

    public ResponseEntity<Sample> findSample(String sampleId, LocalDateTime date) {
        var sample = repository.findByIdAndDate(sampleId, date)
                     .orElseThrow(() -> new NotFoundException(
                             String.format("Sample with id: %s could not be found.",sampleId)
                     ));

        return ResponseEntity.ok(sample);
    }
}
