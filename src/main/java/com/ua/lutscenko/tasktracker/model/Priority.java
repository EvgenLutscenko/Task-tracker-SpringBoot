package com.ua.lutscenko.tasktracker.model;

import lombok.Getter;

@Getter
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public static Priority value(String value) {
        for (Priority priority : values()) {
            if (priority.name().equalsIgnoreCase(value)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Priority.class + "." + value);
    }
}
