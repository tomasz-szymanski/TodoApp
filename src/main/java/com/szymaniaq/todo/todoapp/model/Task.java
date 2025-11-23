package com.szymaniaq.todo.todoapp.model;

import java.time.LocalDate;

public record Task(
        Long id,
        LocalDate date,
        String name,
        String description,
        TaskStatus taskStatus
) {

    public Task(String name) {
        this(null, LocalDate.now(), name, "", TaskStatus.OPEN);
    }

    public Task(String name, String description) {
        this(null, LocalDate.now(), name, description, TaskStatus.OPEN);
    }
}

