package com.example.server.service;

import com.example.server.controller.dto.SignInRequest;
import com.example.server.controller.dto.SignUpRequest;
import com.example.server.controller.dto.UserResponse;
import com.example.server.entity.User;
import com.example.server.exception.InvalidArgumentException;
import com.example.server.repository.UserRepository;
import java.util.Arrays;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean isValidUserName(String userName) {
        if(userRepository.existsUserByNickName(userName)) {
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
            throw new InvalidArgumentException("이미 존재하는 이름입니다.");
        }
        if(!isValidPassword(request.getPassword(), request.getPasswordCheck())){
            throw new InvalidArgumentException("비밀번호가 일치하지 않습니다.");
        }
        User newUser = User
            .builder()
            .nickName(request.getUserName())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(Arrays.asList("ROLE_USER"))
            .build();
        return UserResponse.of(userRepository.save(newUser));
    }

    public UserResponse signIn(SignInRequest request){
        User user = userRepository.findUserByNickName(request.getUserName())
            .orElseThrow(()->new InvalidArgumentException("존재하지 않는 이름입니다."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return UserResponse.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByNickName(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
