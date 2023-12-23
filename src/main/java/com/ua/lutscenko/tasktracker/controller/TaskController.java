package com.ua.lutscenko.tasktracker.controller;


import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskRespDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskUpdateRequestDto;
import com.ua.lutscenko.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public TaskRespDto createTask(@RequestBody @Valid TaskCreateRequestDto dto, Authentication authentication){
        return taskService.create(authentication.getName(), dto);
    }

    @GetMapping
    public List<TaskRespDto> findAll(Authentication authentication){
        return taskService.findAll(authentication.getName());
    }

    @PutMapping("/update")
    public TaskRespDto updateTask(@RequestBody @Valid TaskUpdateRequestDto dto, Authentication authentication){
        return taskService.update(authentication.getName(), dto);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId, Authentication authentication) {
        taskService.delete(taskId, authentication.getName());
        return ResponseEntity.ok("Task deleted successfully");
    }
}
