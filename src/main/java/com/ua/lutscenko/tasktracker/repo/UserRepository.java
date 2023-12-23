package com.ua.lutscenko.tasktracker.repo;

import com.ua.lutscenko.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    void deleteUserByEmail(String email);
}
