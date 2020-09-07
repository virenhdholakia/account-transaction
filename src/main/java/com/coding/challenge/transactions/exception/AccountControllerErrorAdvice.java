package com.coding.challenge.transactions.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class AccountControllerErrorAdvice {

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleCustomerNotFoundException (CustomerNotFoundException cnfe, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), cnfe.getMessage(),
                request.getDescription(false), NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler({InvalidAccountNumberException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidAccountNumberException (InvalidAccountNumberException iane, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), iane.getMessage(),
                request.getDescription(false), NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler({NoDataException.class})
    public ResponseEntity<ExceptionResponse> handleNoDataException (NoDataException nde, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), nde.getMessage(),
                request.getDescription(false), NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(response, NOT_FOUND);
    }
}
