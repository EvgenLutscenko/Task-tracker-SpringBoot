package com.ua.lutscenko.tasktracker.controller;

import com.ua.lutscenko.tasktracker.dto.user.UserLoginRequestDto;
import com.ua.lutscenko.tasktracker.dto.user.UserLoginRespDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterReqDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import com.ua.lutscenko.tasktracker.security.AuthenticationService;
import com.ua.lutscenko.tasktracker.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserRegisterRespDto register(@RequestBody @Valid UserRegisterReqDto userRegisterReqDto){
        return userService.register(userRegisterReqDto);
    }

    @PostMapping("/login")
    public UserLoginRespDto login(@RequestBody @Valid UserLoginRequestDto requestDto, Authentication authentication){
        return authenticationService.authenticate(requestDto);
    }
}
