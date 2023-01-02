package com.example.server.exception;

public class ExpiredAccessException extends RuntimeException {

    public ExpiredAccessException() {
        super();
    }

    public ExpiredAccessException(String message) {
        super(message);
    }
}
