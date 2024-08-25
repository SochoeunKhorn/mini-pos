package com.sochoeun.repository;

import com.sochoeun.dto.CustomerDto;
import com.sochoeun.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdAndDeletedFalse(Long id);
    Page<CustomerDto> findAllByCustomerEmailOrCustomerPhoneAndDeletedFalse(String email,String phone, Pageable pageable);
    Page<CustomerDto> findAllByDeletedFalse(Pageable pageable);

}
