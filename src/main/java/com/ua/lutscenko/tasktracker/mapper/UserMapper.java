package com.ua.lutscenko.tasktracker.mapper;

import com.ua.lutscenko.tasktracker.config.MapperConfig;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterReqDto;
import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import com.ua.lutscenko.tasktracker.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
//    User toModel(UserRegisterReqDto userRegisterReqDto);

    UserRegisterRespDto toDto(User user);
}
