package com.ua.lutscenko.tasktracker.controller;


import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRespDto;
import com.ua.lutscenko.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public Boolean justGet(){
        return true;
    }

    @PostMapping("/create")
    public TaskCreateRespDto createTask(@RequestBody @Valid TaskCreateRequestDto dto, Authentication authentication){
        return taskService.create(authentication.getName(), dto);
    }
}
