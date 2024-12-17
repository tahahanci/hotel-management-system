package com.hancidev.hotelmanagementsystem.service.mapper;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerMapper {

    public Customer customerFromCustomerDto(CustomerDto customerDto) {
        return Customer.builder()
                .customerId(UUID.randomUUID().toString())
                .firstName(customerDto.firstName())
                .lastName(customerDto.lastName())
                .mailAddress(customerDto.mailAddress())
                .build();
    }

    public CustomerResponse customerResponseFromCustomer(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mailAddress(customer.getMailAddress())
                .build();
    }
}
