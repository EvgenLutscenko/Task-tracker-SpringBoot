package com.ua.lutscenko.tasktracker.repo;

import com.ua.lutscenko.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Long, Task> {
}
