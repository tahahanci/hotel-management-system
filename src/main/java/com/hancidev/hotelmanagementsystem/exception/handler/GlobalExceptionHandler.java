package com.hancidev.hotelmanagementsystem.exception.handler;

import com.hancidev.hotelmanagementsystem.exception.CustomerAlreadyExistException;
import com.hancidev.hotelmanagementsystem.exception.CustomerNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public String handleCustomerAlreadyExistException(CustomerAlreadyExistException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public String handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return exception.getMessage();
    }
}
