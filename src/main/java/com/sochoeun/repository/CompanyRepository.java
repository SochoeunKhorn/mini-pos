package com.sochoeun.repository;

import com.sochoeun.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Page<Company> findAllByIsDeletedIsFalse(Pageable pageable);
    Optional<Company> findByIdAndIsDeletedFalse(Long companyId);
}
