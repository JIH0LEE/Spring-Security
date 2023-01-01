package com.example.server.exception;

import com.example.server.common.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(Exception ex) {
        ex.printStackTrace();
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST,null);
    }
}
