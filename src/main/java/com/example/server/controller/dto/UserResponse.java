package com.example.server.controller.dto;

import com.example.server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String username;

    public static UserResponse of(User user){
        return new UserResponse(user.getUserName());
    }
}
