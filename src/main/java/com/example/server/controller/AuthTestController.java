package com.example.server.controller;

import com.example.server.common.ResponseHandler;
import com.example.server.controller.dto.SignInRequest;
import com.example.server.controller.dto.SignUpRequest;
import com.example.server.controller.dto.UserResponse;
import com.example.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthTestController {

    @GetMapping("/test")
    public String test(){
        return "test success";
    }

}
