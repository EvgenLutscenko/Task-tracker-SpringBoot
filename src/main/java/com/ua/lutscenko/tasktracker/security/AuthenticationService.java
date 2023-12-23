package com.ua.lutscenko.tasktracker.security;

import com.ua.lutscenko.tasktracker.dto.user.UserLoginRequestDto;
import com.ua.lutscenko.tasktracker.dto.user.UserLoginRespDto;
import com.ua.lutscenko.tasktracker.model.User;
import com.ua.lutscenko.tasktracker.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final UserRepository userRepository;

    public UserLoginRespDto authenticate(UserLoginRequestDto request){
        final Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String email = authentication.getName();

        updateUserLastLoginDate(email);

        String token = jwtUtil.generateToken(request.getEmail());
        return new UserLoginRespDto(token);
    }

    private void updateUserLastLoginDate(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("user with email: " + email + " not found")
        );

        if (user != null) {
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
