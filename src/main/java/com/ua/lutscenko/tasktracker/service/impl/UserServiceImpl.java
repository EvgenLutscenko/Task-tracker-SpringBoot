package com.ua.lutscenko.tasktracker.service.impl;

import com.ua.lutscenko.tasktracker.dto.user.UserRegisterReqDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import com.ua.lutscenko.tasktracker.exception.EntityIsAlreadyExistException;
import com.ua.lutscenko.tasktracker.mapper.UserMapper;
import com.ua.lutscenko.tasktracker.model.User;
import com.ua.lutscenko.tasktracker.repo.UserRepository;
import com.ua.lutscenko.tasktracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
