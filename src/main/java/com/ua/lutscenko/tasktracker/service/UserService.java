package com.ua.lutscenko.tasktracker.service;

import com.ua.lutscenko.tasktracker.dto.user.UserRegisterReqDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import org.springframework.stereotype.Service;

public interface UserService {
    UserRegisterRespDto register(UserRegisterReqDto userRegisterReqDto);
}
