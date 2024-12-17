package com.hancidev.hotelmanagementsystem.service.impl;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.entity.Customer;
import com.hancidev.hotelmanagementsystem.repository.CustomerRepository;
import com.hancidev.hotelmanagementsystem.service.CustomerService;
import com.hancidev.hotelmanagementsystem.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerFromCustomerDto(customerDto);
        customerRepository.save(customer);
        return customerMapper.customerResponseFromCustomer(customer);
    }
}
