package com.ua.lutscenko.tasktracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
