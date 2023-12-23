package com.ua.lutscenko.tasktracker.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDeleteRequestDto {
    @NotBlank
    @Size(min = 5)
    private String email;
}
