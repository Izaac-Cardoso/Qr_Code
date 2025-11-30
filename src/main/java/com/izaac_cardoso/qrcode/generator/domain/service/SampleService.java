package com.izaac_cardoso.qrcode.generator.domain.service;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.exceptions.NotFoundException;
import com.izaac_cardoso.qrcode.generator.repository.CollectedSampleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

    public Sample findSample(String sampleId, LocalDateTime date) {
        Sample sample = repository.findById(sampleId)
                        .orElseThrow(() -> new NotFoundException("Sample with id:" + sampleId + "could not be found."));

        return sample;
    }

}
