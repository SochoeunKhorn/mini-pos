package com.sochoeun.service;

import com.sochoeun.dto.CustomerDto;
import com.sochoeun.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Page<CustomerDto> getCustomers(Map<String,String> params);
    Customer getCustomer(Long customerId);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long customerId);
}
