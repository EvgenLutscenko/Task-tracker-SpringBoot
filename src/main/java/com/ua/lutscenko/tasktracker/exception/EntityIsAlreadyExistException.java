package com.ua.lutscenko.tasktracker.exception;

import com.ua.lutscenko.tasktracker.dto.user.UserRegisterRespDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EntityIsAlreadyExistException extends RuntimeException{
    public EntityIsAlreadyExistException(String message) {
        super(message);
    }
}
