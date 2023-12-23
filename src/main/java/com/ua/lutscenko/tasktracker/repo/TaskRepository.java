package com.ua.lutscenko.tasktracker.repo;

import com.ua.lutscenko.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> getTaskById(Long id);
    List<Task> findAllByUserEmail(String email);
    void deleteTaskById(Long id);
}
