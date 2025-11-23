package com.szymaniaq.todo.todoapp.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum TaskStatus {
    OPEN(1),
    IN_PROGRESS(2),
    CLOSED(3);

    private final int value;

}
