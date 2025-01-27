package com.example.appGestioneAziendale.handler;

import com.example.appGestioneAziendale.domain.dto.response.ErrorResponse;
import com.example.appGestioneAziendale.domain.exceptions.IllegalTransactionException;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(MyEntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(MyEntityNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalTransactionException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalTransactionException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(IllegalTransactionException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(IllegalTransactionException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }




}
