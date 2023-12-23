package com.ua.lutscenko.tasktracker.service.impl;

import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskRespDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskUpdateRequestDto;
import com.ua.lutscenko.tasktracker.exception.TaskIdIsNotExistInUserException;
import com.ua.lutscenko.tasktracker.exception.TaskNotFoundException;
import com.ua.lutscenko.tasktracker.mapper.TaskMapper;
import com.ua.lutscenko.tasktracker.model.Task;
import com.ua.lutscenko.tasktracker.model.User;
import com.ua.lutscenko.tasktracker.repo.TaskRepository;
import com.ua.lutscenko.tasktracker.repo.UserRepository;
import com.ua.lutscenko.tasktracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public TaskRespDto create(String name, TaskCreateRequestDto dto) {
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new UsernameNotFoundException("user with email " + name + " is not exist")
        );

        Task model = taskMapper.toModel(dto);
        if(model.isCompleted() && model.getDueDate().isBefore(LocalDateTime.now())){
            model.setCompleted(false);
        }
        model.setUser(user);

        Task save = taskRepository.save(model);
        TaskRespDto dto1 = taskMapper.toDto(save);
        return dto1;
    }

    @Override
    @Transactional
    public List<TaskRespDto> findAll(String name) {
        List<Task> allByUserEmail = taskRepository.findAllByUserEmail(name);
        updateCompletedStatus(allByUserEmail);

        return allByUserEmail.stream()
                .map(taskMapper::toDto).toList();
    }

    @Override
    public TaskRespDto update(String name, TaskUpdateRequestDto dto) {
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new UsernameNotFoundException("user with email " + name + " is not exist")
        );

        Task task = taskRepository.getTaskById(dto.getId()).orElseThrow(
                () -> new TaskNotFoundException("task with id - " + dto.getId() + " isn't exist")
        );

        if(!Objects.equals(task.getUser().getId(), user.getId())){
            throw new TaskIdIsNotExistInUserException("user doesn't have a task with id - " + task.getId());
        }

        Task model = taskMapper.toModel(dto);

        updateFields(model, task);

        return taskMapper.toDto(taskRepository.save(model));
    }

    @Override
    @Transactional
    public void delete(Long id, String name) {
        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new UsernameNotFoundException("user with email " + name + " is not exist")
        );

        Task task = taskRepository.getTaskById(id).orElseThrow(
                () -> new TaskNotFoundException("task with id - " + id + " isn't exist")
        );

        if(!Objects.equals(task.getUser().getId(), user.getId())){
            throw new TaskIdIsNotExistInUserException("user doesn't have a task with id - " + task.getId());
        }
        taskRepository.deleteTaskById(id);
    }

    public void updateCompletedStatus(List<Task> taskList){
        for (Task task : taskList){
            if(task.isCompleted() && task.getDueDate().isBefore(LocalDateTime.now())){
                task.setCompleted(false);
            }
        }
    }
    private void updateFields(Task target, Task source){
        if(target.getUser() == null){
            target.setUser(source.getUser());
        }

        if(target.getTitle() == null){
            target.setTitle(source.getTitle());
        }

        if(target.getDescription() == null){
            target.setDescription(source.getDescription());
        }

        if(target.getPriority() == null){
            target.setPriority(source.getPriority());
        }

        if(target.getDueDate() == null){
            target.setDueDate(source.getDueDate());
        }

        if(target.getCreatedAt() == null){
            target.setCreatedAt(source.getCreatedAt());
        }
    }
}
