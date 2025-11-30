package com.izaac_cardoso.qrcode.generator.repository;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface CollectedSampleRepository extends JpaRepository<Sample, String> {

    public Optional<Sample> findByIdAndDate(String id, LocalDateTime date);
}
