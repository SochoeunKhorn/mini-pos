package com.sochoeun.mapper;

import com.sochoeun.dto.CustomerDto;
import com.sochoeun.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .customerLocalName(customerDto.getCustomerLocalName())
                .customerEngName(customerDto.getCustomerEngName())
                .customerEmail(customerDto.getCustomerEmail())
                .customerPhone(customerDto.getCustomerPhone())
                .customerAddress(customerDto.getCustomerAddress())
                .build();
    }

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .customerLocalName(customer.getCustomerLocalName())
                .customerEngName(customer.getCustomerEngName())
                .customerEmail(customer.getCustomerEmail())
                .customerPhone(customer.getCustomerPhone())
                .customerAddress(customer.getCustomerAddress())
                .build();
    }
}
