package com.ua.lutscenko.tasktracker.service;

import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskRespDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskUpdateRequestDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TaskService {
    TaskRespDto create(String name, TaskCreateRequestDto dto);

    TaskRespDto update(String name, TaskUpdateRequestDto dto);

    List<TaskRespDto> findAll(Authentication authentication);

    void delete(Long id, String name);

}
