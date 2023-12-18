package com.ua.lutscenko.tasktracker.repo;

import com.ua.lutscenko.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
