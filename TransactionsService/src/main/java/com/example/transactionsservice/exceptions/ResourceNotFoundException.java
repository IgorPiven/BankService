package com.example.transactionsservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super (message);
    }
}
