package com.ua.lutscenko.tasktracker.service;

import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRespDto;

public interface TaskService {
    TaskCreateRespDto create(String name, TaskCreateRequestDto dto);
}
