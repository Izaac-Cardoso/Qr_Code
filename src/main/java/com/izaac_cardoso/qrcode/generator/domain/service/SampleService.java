package com.izaac_cardoso.qrcode.generator.domain.service;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import com.izaac_cardoso.qrcode.generator.domain.exceptions.NotFoundException;
import com.izaac_cardoso.qrcode.generator.repository.CollectedSampleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public ResponseEntity<Sample> findSample(String sampleId, LocalDate date) {
        var sample = repository.findByIdAndDate(sampleId, date)
                     .orElseThrow(() -> new NotFoundException(
                             String.format("Sample with id: %s could not be found.",sampleId)
                     ));

        return ResponseEntity.ok(sample);
    }

    public ResponseEntity<List<Sample>> getSampleById(String id, int pageNumber) {
        Sort sort = Sort.by("date").ascending();
        Pageable pageable = PageRequest.of(pageNumber, 5, sort);

        var samples = repository.findAllById(id, pageable).getContent();

        return ResponseEntity.ok(samples);
    }

    public ResponseEntity<List<Sample>> findAllByCustomDate(LocalDate init, LocalDate end) {
        if(end.isBefore(init)) {
            throw new DateTimeException("The final date cannot be before the initial date.");
        }
        var listOfSample = repository.findAllByDateBetween(init, end);

        return ResponseEntity.ok(listOfSample);
    }

}
