package com.example.server.controller.dto;

import lombok.Getter;

@Getter
public class RefreshRequest {

    private String accessToken;

    private String refreshToken;

}
