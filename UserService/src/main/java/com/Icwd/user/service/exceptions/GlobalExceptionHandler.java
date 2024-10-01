package com.Icwd.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Icwd.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder()
                                          .message(message)
                                          .success(false)  // Set to false for error scenarios
                                          .status(HttpStatus.NOT_FOUND)
                                          .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
