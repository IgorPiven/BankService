package com.example.transactionsservice.errors;

import com.example.transactionsservice.dto.ErrorDto;
import com.example.transactionsservice.exceptions.IllegalOperationException;
import com.example.transactionsservice.exceptions.ResourceExistsButEmptyException;
import com.example.transactionsservice.exceptions.ResourceNotAvailableException;
import com.example.transactionsservice.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchIllegalOperationException(IllegalOperationException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchResourceExistButEmptyException(ResourceExistsButEmptyException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.OK.value(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotAvailableException(ResourceNotAvailableException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
