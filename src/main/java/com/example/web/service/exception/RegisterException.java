package com.example.web.service.exception;

public class RegisterException extends RuntimeException{
    public RegisterException() {

    }

    public RegisterException(String message) {
        super(message);
    }
}
