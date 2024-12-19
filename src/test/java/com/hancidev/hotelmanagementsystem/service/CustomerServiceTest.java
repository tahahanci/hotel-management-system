package com.hancidev.hotelmanagementsystem.service;

import com.hancidev.hotelmanagementsystem.dto.CustomerDto;
import com.hancidev.hotelmanagementsystem.dto.response.CustomerResponse;
import com.hancidev.hotelmanagementsystem.entity.Customer;
import com.hancidev.hotelmanagementsystem.exception.CustomerAlreadyExistException;
import com.hancidev.hotelmanagementsystem.exception.CustomerNotFoundException;
import com.hancidev.hotelmanagementsystem.repository.CustomerRepository;
import com.hancidev.hotelmanagementsystem.service.impl.CustomerServiceImpl;
import com.hancidev.hotelmanagementsystem.service.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDto customerDto;
    private Customer customer;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        customerDto = CustomerDto.builder()
                .firstName("test")
                .lastName("test-last-name")
                .mailAddress("test@gmail.com")
                .build();

        customer = Customer.builder()
                .id(1L)
                .customerId("A-12345")
                .firstName("test")
                .lastName("test-last-name")
                .mailAddress("test@gmail.com")
                .build();

        customerResponse = CustomerResponse.builder()
                .customerId("A-12345")
                .firstName("test")
                .lastName("test-last-name")
                .mailAddress("test@gmail.com")
                .build();
    }

    @Test
    void shouldCreateCustomer_IfCustomerMailDoesNotExist() {
        when(customerRepository.findCustomerByMailAddress(customerDto.mailAddress()))
                .thenReturn(Optional.empty());
        when(customerMapper.customerFromCustomerDto(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.customerResponseFromCustomer(customer)).thenReturn(customerResponse);

        CustomerResponse actualResponse = customerService.createCustomer(customerDto);

        assertThat(actualResponse)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(customerResponse);

        verify(customerRepository).findCustomerByMailAddress(customerDto.mailAddress());
        verify(customerRepository).save(customer);
        verify(customerMapper).customerFromCustomerDto(customerDto);
        verify(customerMapper).customerResponseFromCustomer(customer);
    }

    @Test
    void shouldThrowCustomerAlreadyFoundException_WhenCustomerIsExist() {
        when(customerRepository.findCustomerByMailAddress(customerDto.mailAddress()))
                .thenReturn(Optional.of(customer));

        assertThatThrownBy(() -> customerService.createCustomer(customerDto))
                .isInstanceOf(CustomerAlreadyExistException.class)
                .hasMessageContaining("Customer already exists with given mail: " + customerDto.mailAddress());

        verify(customerRepository).findCustomerByMailAddress(customerDto.mailAddress());
        verify(customerMapper, never()).customerFromCustomerDto(customerDto);
        verify(customerRepository, never()).save(customer);
        verify(customerMapper, never()).customerResponseFromCustomer(customer);
    }

    @Test
    void shouldReturnCustomer_IfCustomerExist() {
        String customerId = "A-12345";

        when(customerRepository.findCustomerByCustomerId(customerId))
                .thenReturn(Optional.of(customer));
        when(customerMapper.customerResponseFromCustomer(customer)).thenReturn(customerResponse);

        CustomerResponse actualResponse = customerService.findCustomer(customerId);

        assertThat(actualResponse)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(customerResponse);

        verify(customerRepository).findCustomerByCustomerId(customerId);
        verify(customerMapper).customerResponseFromCustomer(customer);
    }

    @Test
    void shouldThrowCustomerDoesNotException_IfCustomerDoesNotExist() {
        String customerId = "ALM123";
        when(customerRepository.findCustomerByCustomerId(customerId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.findCustomer(customerId))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Customer does not exist with given ID: " + customerId);

        verify(customerRepository).findCustomerByCustomerId(customerId);
        verify(customerMapper, never()).customerResponseFromCustomer(customer);
    }
}
