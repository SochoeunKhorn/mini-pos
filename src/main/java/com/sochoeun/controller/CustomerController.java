package com.sochoeun.controller;

import com.sochoeun.dto.CustomerDto;
import com.sochoeun.dto.PageResponse;
import com.sochoeun.mapper.CustomerMapper;
import com.sochoeun.model.Customer;
import com.sochoeun.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "CUSTOMER")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto request) {
        Customer customer = customerService.createCustomer(customerMapper.toCustomer(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toCustomerDto(customer));
    }

    @GetMapping
    public ResponseEntity<?> getCustomers(@RequestParam(required = false) Map<String, String> request) {
        Page<CustomerDto> customers = customerService.getCustomers(request);
        PageResponse response = new PageResponse(customers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<?> getCustomer(@PathVariable("customer-id") Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toCustomerDto(customer));
    }

    @PutMapping
    public ResponseEntity<?> deleteCustomer(@RequestBody CustomerDto request) {
        Customer updated = customerService.updateCustomer(customerMapper.toCustomer(request));
        return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toCustomerDto(updated));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customer-id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }

}
