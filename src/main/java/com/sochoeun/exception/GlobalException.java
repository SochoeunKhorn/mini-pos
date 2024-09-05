package com.sochoeun.exception;

import com.sochoeun.handler.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalException {


    /* -- NotFoundException --*/
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND, e.getSmg());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /* -- Input Validation --*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    var errorField = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(errorField, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    /* -- Duplicate Value in field -- */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Custom error message
        String errorMessage = "Duplicate key error: A record with the same unique value already exists.";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    /* -- Value is not valid -- */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String customErrorMessage = "Invalid value for ProductType. Please use one of the allowed values: [SERVICE, STANDARD].";
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, customErrorMessage);
        // You can return a custom error response object or just a message
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /* -- Global Exception --*/
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGlobalException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
