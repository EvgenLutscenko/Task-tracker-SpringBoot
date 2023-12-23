package com.ua.lutscenko.tasktracker.controller;

import com.ua.lutscenko.tasktracker.dto.user.UserDeleteRequestDto;
import com.ua.lutscenko.tasktracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @DeleteMapping ("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteRequestDto dto, Authentication authentication){
        userService.deleteUser(dto.getEmail(), authentication);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
