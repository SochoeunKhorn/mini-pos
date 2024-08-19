package com.sochoeun.exception;


import org.springframework.http.HttpStatus;


public record ApiResponse(HttpStatus status, String message) {
}
