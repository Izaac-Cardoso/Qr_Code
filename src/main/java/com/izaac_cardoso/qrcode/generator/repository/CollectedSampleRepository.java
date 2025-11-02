package com.izaac_cardoso.qrcode.generator.repository;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CollectedSampleRepository extends JpaRepository<Sample, String> {
}
