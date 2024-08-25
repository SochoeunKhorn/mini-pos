package com.sochoeun.service.impl;

import com.sochoeun.dto.CustomerDto;
import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Customer;
import com.sochoeun.repository.CustomerRepository;
import com.sochoeun.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerDto> getCustomers(Map<String, String> params) {

        int offset = 0;
        int limit = 10;

        String email = "";
        String phone = "";

        if (params.containsKey("offset")){
            offset = Integer.parseInt(params.get("offset"));
        }
        if (params.containsKey("limit")){
            limit = Integer.parseInt(params.get("limit"));
        }

        if (offset <= 0){
            offset = 1;
        }

        Pageable pageable = PageRequest.of(offset-1,limit);
        if (params.containsKey("email")){
            email = params.get("email");
            return customerRepository.findAllByCustomerEmailOrCustomerPhoneAndDeletedFalse(email,phone,pageable);
        } else if (params.containsKey("phone")){
            phone = params.get("phone");
            return customerRepository.findAllByCustomerEmailOrCustomerPhoneAndDeletedFalse(email,phone,pageable);
        }else
            return customerRepository.findAllByDeletedFalse(pageable);
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findByIdAndDeletedFalse(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id " + customerId));
    }

    @Override
    public Customer updateCustomer(Customer request) {
        Customer customer = getCustomer(request.getId());
        customer.setCustomerLocalName(request.getCustomerLocalName());
        customer.setCustomerEngName(request.getCustomerEngName());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setCustomerPhone(request.getCustomerPhone());
        customer.setCustomerAddress(request.getCustomerAddress());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = getCustomer(customerId);
        customer.setDeleted(true);
        customerRepository.save(customer);
    }
}
