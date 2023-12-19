package com.ua.lutscenko.tasktracker.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    @GetMapping
    public Boolean justGet(){
        return true;
    }
}
