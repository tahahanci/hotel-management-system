package com.hancidev.hotelmanagementsystem.service.impl;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.entity.Customer;
import com.hancidev.hotelmanagementsystem.exception.CustomerAlreadyExistException;
import com.hancidev.hotelmanagementsystem.repository.CustomerRepository;
import com.hancidev.hotelmanagementsystem.service.CustomerService;
import com.hancidev.hotelmanagementsystem.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByMailAddress(customerDto.mailAddress());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exists with given mail: " + customerDto.mailAddress());
        }

        Customer customer = customerRepository.save(customerMapper.customerFromCustomerDto(customerDto));
        return customerMapper.customerResponseFromCustomer(customer);
    }
}
