package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerDto customerDto);
}
