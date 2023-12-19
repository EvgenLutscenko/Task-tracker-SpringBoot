package com.ua.lutscenko.tasktracker.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDto {
    @NotBlank
    @Size(min = 4)
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
}
