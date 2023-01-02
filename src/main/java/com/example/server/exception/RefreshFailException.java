package com.example.server.exception;

public class RefreshFailException extends RuntimeException {

    public RefreshFailException() {
        super();
    }

    public RefreshFailException(String message) {
        super(message);
    }
}
