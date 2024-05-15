package com.example.transactionsservice.exceptions;

public class ResourceExistsButEmptyException extends RuntimeException {

    public ResourceExistsButEmptyException(String message) {
        super (message);
    }
}
