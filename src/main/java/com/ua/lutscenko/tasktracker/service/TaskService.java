package com.ua.lutscenko.tasktracker.service;

import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskRespDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskUpdateRequestDto;

import java.util.List;

public interface TaskService {
    TaskRespDto create(String name, TaskCreateRequestDto dto);

    TaskRespDto update(String name, TaskUpdateRequestDto dto);

    List<TaskRespDto> findAll(String name);

    void delete(Long id, String name);
}
