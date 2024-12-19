package com.hancidev.hotelmanagementsystem.controller;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerUpdateRequest;
import com.hancidev.hotelmanagementsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @PutMapping("/update/{mailAddress}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String mailAddress,
                                                           @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(mailAddress, request));
    }

    @GetMapping("/find/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.findCustomer(customerId));
    }
}
