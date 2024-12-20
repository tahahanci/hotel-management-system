package com.hancidev.hotelmanagementsystem.service.impl;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerUpdateRequest;
import com.hancidev.hotelmanagementsystem.entity.Customer;
import com.hancidev.hotelmanagementsystem.exception.CustomerAlreadyExistException;
import com.hancidev.hotelmanagementsystem.exception.CustomerNotFoundException;
import com.hancidev.hotelmanagementsystem.repository.CustomerRepository;
import com.hancidev.hotelmanagementsystem.service.CustomerService;
import com.hancidev.hotelmanagementsystem.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public CustomerResponse updateCustomer(String mail, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findCustomerByMailAddress(mail)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist with given mail: " + mail));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setMailAddress(request.mailAddress());
        customerRepository.save(customer);
        return customerMapper.customerResponseFromCustomer(customer);
    }

    @Override
    public CustomerResponse findCustomer(String customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist with given ID: " + customerId));
        return customerMapper.customerResponseFromCustomer(customer);
    }

    @Override
    public CustomerResponse deleteCustomer(String customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist with given ID: " + customerId));
        customer.setActive(false);
        customerRepository.save(customer);
        return customerMapper.customerResponseFromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> showActiveCustomers() {
        return customerRepository.getCustomersByActive(true)
                .stream()
                .map(customerMapper::customerResponseFromCustomer)
                .toList();
    }
}
