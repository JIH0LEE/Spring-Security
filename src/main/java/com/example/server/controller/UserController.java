package com.example.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
public class UserController {

    @GetMapping
    public String test(){
        return "hello world";
    }
}
