package com.sochoeun.repository;

import com.sochoeun.dto.SupplierDto;
import com.sochoeun.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByIdAndDeletedFalse(Long id);
    List<Supplier> findAllByDeletedFalse();
    Page<SupplierDto> findAllByDeletedFalseAndSupplierLocalNameContainingIgnoreCase(String localName,Pageable pageable);
    Page<SupplierDto> findAllByDeletedFalse(Pageable pageable);
}
