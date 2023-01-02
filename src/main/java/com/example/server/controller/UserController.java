package com.example.server.controller;

import com.example.server.common.ResponseHandler;
import com.example.server.controller.dto.RefreshRequest;
import com.example.server.controller.dto.SignInRequest;
import com.example.server.controller.dto.SignUpRequest;
import com.example.server.controller.dto.SingInResponse;
import com.example.server.controller.dto.UserResponse;
import com.example.server.exception.RefreshFailException;
import com.example.server.jwt.JwtTokenProvider;
import com.example.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> singUp(@RequestBody SignUpRequest request){
        UserResponse data = userService.signUp(request);
        return ResponseHandler.generateResponse("회원가입이 완료되었습니다.",HttpStatus.CREATED,data);
    }

    @Transactional
    @PostMapping("/sign-in")
    public ResponseEntity<Object> singIn(@RequestBody SignInRequest request){
        UserResponse userInfo = userService.signIn(request);
        String accessToken = jwtTokenProvider.createAccessToken(userInfo.getUsername(),userInfo.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        userService.refreshToken(userInfo,refreshToken);
        SingInResponse data = SingInResponse.of(userInfo,accessToken,refreshToken);
        return ResponseHandler.generateResponse("로그인이 완료되었습니다.",HttpStatus.OK,data);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody RefreshRequest request){
        SingInResponse data = jwtTokenProvider.refreshToken(request);
        if(data!=null){
            return ResponseHandler.generateResponse("토큰이 갱신되었습니다.",HttpStatus.OK,data);
        }
        else{
            throw new RefreshFailException("토큰 갱신이 실패하였습니다.");
        }
    }

}
