package com.sochoeun.repository;

import com.sochoeun.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByIdAndDeletedFalse(Long id);
    List<ExchangeRate> findAllByDeletedFalse();
}
