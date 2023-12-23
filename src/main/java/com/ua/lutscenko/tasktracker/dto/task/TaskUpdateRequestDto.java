package com.ua.lutscenko.tasktracker.dto.task;

import com.ua.lutscenko.tasktracker.model.Priority;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateRequestDto {
    @NotNull
    @Min(value = 0)
    private Long id;

    @NotNull
    @Min(value = 0)
    private Long userId;

    private String title;

    private String description;

    @Pattern(regexp = "^high$|^medium$|^low$", message = "allowed input: high, medium, low")
    private String priority;

    private LocalDateTime dueDate;

    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String isCompleted;
}

