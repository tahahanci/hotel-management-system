package com.hancidev.hotelmanagementsystem.exception;

public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
