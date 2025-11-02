package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(Map.of(
                        "status", ex.getCode(),
                        "error", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnexpected(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(Map.of(
                        "status", 500,
                        "error", "Internal Server Error"
                ));
    }
}
