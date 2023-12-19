package com.ua.lutscenko.tasktracker.security;

import com.ua.lutscenko.tasktracker.dto.user.UserLoginRequestDto;
import com.ua.lutscenko.tasktracker.dto.user.UserLoginRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;

    public UserLoginRespDto authenticate(UserLoginRequestDto request){
        final Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getEmail());
        return new UserLoginRespDto(token);
    }
}
