package com.hancidev.hotelmanagementsystem.repository;

import com.hancidev.hotelmanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerId(String customerId);

    Optional<Customer> findCustomerByMailAddress(String mailAddress);
}
