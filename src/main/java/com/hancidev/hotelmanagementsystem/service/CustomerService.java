package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerUpdateRequest;
import com.hancidev.hotelmanagementsystem.dto.response.GenderDistributionResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerDto customerDto);

    CustomerResponse updateCustomer(String mail, CustomerUpdateRequest request);

    CustomerResponse findCustomer(String customerId);

    CustomerResponse deleteCustomer(String customerId);

    List<CustomerResponse> showActiveCustomers();
}
