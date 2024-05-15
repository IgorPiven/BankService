package com.example.transactionsservice.exceptions;

public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String message) {
        super (message);
    }
}
