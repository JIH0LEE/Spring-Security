package com.example.server.controller.dto;

import com.example.server.entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingInResponse {

    private UserResponse user;

    private String accessToken;

    private String refreshToken;

    public static SingInResponse of(UserResponse user,String accessToken,String refreshToken){
        return new SingInResponse(user,accessToken,refreshToken);
    }
}
