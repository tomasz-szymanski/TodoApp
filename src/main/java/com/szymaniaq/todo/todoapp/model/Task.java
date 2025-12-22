package com.szymaniaq.todo.todoapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Task(
        Long id,
        LocalDate date,
        LocalDateTime deadline,
        String name,
        String description,
        TaskStatus taskStatus
) {

    public Task(String name) {
        this(null, LocalDate.now(), null, name, "", TaskStatus.OPEN);
    }

    public Task(String name, String description) {
        this(null, LocalDate.now(),null, name, description, TaskStatus.OPEN);
    }
}

