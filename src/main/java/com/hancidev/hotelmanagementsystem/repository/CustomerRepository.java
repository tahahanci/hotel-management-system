package com.hancidev.hotelmanagementsystem.repository;

import com.hancidev.hotelmanagementsystem.entity.Customer;
import com.hancidev.hotelmanagementsystem.entity.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerId(String customerId);

    Optional<Customer> findCustomerByMailAddress(String mailAddress);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.gender = :gender")
    Long showGenderDistribution(Gender gender);

    List<Customer> getCustomersByActive(boolean active);
}
