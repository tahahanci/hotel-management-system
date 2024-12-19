package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerUpdateRequest;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerDto customerDto);

    CustomerResponse updateCustomer(String mail, CustomerUpdateRequest request);

    CustomerResponse findCustomer(String customerId);
}
