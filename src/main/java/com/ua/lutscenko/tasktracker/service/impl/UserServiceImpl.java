package com.ua.lutscenko.tasktracker.service.impl;

import com.ua.lutscenko.tasktracker.dto.user.UserRegisterReqDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import com.ua.lutscenko.tasktracker.exception.EntityIsAlreadyExistException;
import com.ua.lutscenko.tasktracker.mapper.UserMapper;
import com.ua.lutscenko.tasktracker.model.User;
import com.ua.lutscenko.tasktracker.repo.UserRepository;
import com.ua.lutscenko.tasktracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserRegisterRespDto register(UserRegisterReqDto userRegisterReqDto) {
        if(userRepository.findByEmail(userRegisterReqDto.getEmail()).isPresent()){
            throw new EntityIsAlreadyExistException(
                    "entity with email: " + userRegisterReqDto.getEmail()
                    + " is already exist"
            );
        }

        User user = new User();
        user.setEmail(userRegisterReqDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterReqDto.getPassword()));
        user.setUsername(userRegisterReqDto.getUsername());

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(String email, Authentication authentication) {
        if(isAdmin(authentication)){
            if(!userRepository.existsByEmail(email)){
                throw new UsernameNotFoundException(
                        "user with email: " + email + " not found"
                );
            }
            userRepository.deleteUserByEmail(email);
        }
    }

    private boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

}
