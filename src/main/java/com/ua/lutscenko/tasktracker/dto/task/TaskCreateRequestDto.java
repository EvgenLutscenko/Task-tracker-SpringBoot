package com.ua.lutscenko.tasktracker.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateRequestDto {
    private String title;

    private String description;

    @NotNull
    @Pattern(regexp = "^high$|^medium$|^low$", message = "allowed input: high, medium, low")
    private String priority;

    @NotNull
    private LocalDateTime dueDate;

    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String isCompleted;
}
