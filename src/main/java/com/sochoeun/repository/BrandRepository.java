package com.sochoeun.repository;

import com.sochoeun.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // sorting brands by name
    List<Brand> findAllByNameContainingIgnoreCase(String brandName);
    Page<Brand> findAllByNameContainingIgnoreCase(String brandName, Pageable pageable);

    Optional<Brand> findByIdAndIsDeletedFalse(Long id);
    List<Brand> findAllByNameContainingIgnoreCaseAndIsDeletedFalse(String brandName);
    List<Brand> findAllByIsDeletedIsFalse();
}
