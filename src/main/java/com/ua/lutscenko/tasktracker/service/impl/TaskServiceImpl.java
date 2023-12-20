package com.ua.lutscenko.tasktracker.service.impl;

import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRespDto;
import com.ua.lutscenko.tasktracker.mapper.TaskMapper;
import com.ua.lutscenko.tasktracker.model.Task;
import com.ua.lutscenko.tasktracker.model.User;
import com.ua.lutscenko.tasktracker.repo.TaskRepository;
import com.ua.lutscenko.tasktracker.repo.UserRepository;
import com.ua.lutscenko.tasktracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public TaskCreateRespDto create(String name, TaskCreateRequestDto dto) {
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new UsernameNotFoundException("user with email " + name + " is not exist")
        );

        Task model = taskMapper.toModel(dto);
        if(model.isCompleted() && model.getDueDate().isBefore(LocalDateTime.now())){
            model.setCompleted(false);
        }
        model.setUser(user);

        Task save = taskRepository.save(model);
        TaskCreateRespDto dto1 = taskMapper.toDto(save);
        return dto1;
    }
}
