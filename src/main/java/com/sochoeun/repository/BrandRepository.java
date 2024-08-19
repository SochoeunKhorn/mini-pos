package com.sochoeun.repository;

import com.sochoeun.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // sorting brands by name
    List<Brand> findAllByNameContaining(String brandName);
    Page<Brand> findAllByNameContaining(String brandName, Pageable pageable);
}
