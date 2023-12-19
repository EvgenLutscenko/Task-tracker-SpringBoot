package com.ua.lutscenko.tasktracker.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegisterRespDto {
    private Long id;

    private String email;

    private String username;

    private LocalDateTime registrationDate;
}
