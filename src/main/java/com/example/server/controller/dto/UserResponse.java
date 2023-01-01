package com.example.server.controller.dto;

import com.example.server.entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String username;

    private List roles;

    public static UserResponse of(User user){
        return new UserResponse(user.getNickName(),user.getRoles());
    }
}
