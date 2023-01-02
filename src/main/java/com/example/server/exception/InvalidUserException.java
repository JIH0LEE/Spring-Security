package com.example.server.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
