package com.example.server.controller.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String userName;

    private String password;

    private String passwordCheck;
}
