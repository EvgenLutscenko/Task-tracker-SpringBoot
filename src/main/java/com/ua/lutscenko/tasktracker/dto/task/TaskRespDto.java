package com.ua.lutscenko.tasktracker.dto.task;

import com.ua.lutscenko.tasktracker.model.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRespDto {
    private Long id;

    private Long userId;

    private String title;

    private String description;

    private Priority priority;

    private LocalDateTime dueDate;

    private Boolean isCompleted;

    private LocalDateTime createdAt;
}
