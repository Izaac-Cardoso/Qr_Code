package com.izaac_cardoso.qrcode.generator.repository;

import com.izaac_cardoso.qrcode.generator.domain.entities.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface CollectedSampleRepository extends JpaRepository<Sample, String> {

    Optional<Sample> findByIdAndDate(String id, LocalDate date);

//  @Query(value = "SELECT s FROM samples s WHERE s.id =? ORDER BY s.date ASC")
//  List<Optional<Sample>> findAllById(String id);

    Page<Sample> findAllById(String id, Pageable pageable);

    List<Sample> findAllByDateBetween(LocalDate initDate, LocalDate endDate);
}
