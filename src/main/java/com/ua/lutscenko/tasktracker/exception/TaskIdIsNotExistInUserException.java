package com.ua.lutscenko.tasktracker.exception;

public class TaskIdIsNotExistInUserException extends RuntimeException {
    public TaskIdIsNotExistInUserException(String message) {
        super(message);
    }
}
