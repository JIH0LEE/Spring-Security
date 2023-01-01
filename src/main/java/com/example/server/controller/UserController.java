package com.example.server.controller;

import com.example.server.common.ResponseHandler;
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
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> singUp(@RequestBody SignUpRequest request){
        UserResponse data = userService.signUp(request);
        return ResponseHandler.generateResponse("회원가입이 완료되었습니다.",HttpStatus.CREATED,data);
    }

}
