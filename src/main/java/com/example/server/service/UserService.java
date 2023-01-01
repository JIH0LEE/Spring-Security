package com.example.server.service;

import com.example.server.controller.dto.SignUpRequest;
import com.example.server.controller.dto.UserResponse;
import com.example.server.entity.User;
import com.example.server.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean isValidUserName(String userName) {
        if(userRepository.existsUserByUserName(userName)) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String passwordCheck) {
        if(password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

    public UserResponse signUp(SignUpRequest request){
        if(!isValidUserName(request.getUserName())){
            throw new RuntimeException();
        }
        if(!isValidPassword(request.getPassword(), request.getPasswordCheck())){
            throw new RuntimeException();
        }
        User newUser = User
            .builder()
            .userName(request.getUserName())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
        return UserResponse.of(userRepository.save(newUser));
    }
}
