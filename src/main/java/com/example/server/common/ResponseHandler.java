package com.example.server.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> object = new HashMap<String, Object>();
        object.put("message", message);
        object.put("status", status.value());
        object.put("data", responseObj);
        return ResponseEntity
            .status(status)
            .body(object);
    }
}
