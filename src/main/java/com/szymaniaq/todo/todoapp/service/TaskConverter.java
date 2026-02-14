package com.szymaniaq.todo.todoapp.service;

import com.szymaniaq.todo.todoapp.model.Task;
import com.szymaniaq.todo.todoapp.repository.TaskEntity;

public class TaskConverter {
    public static Task toModel(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getDate(),
                entity.getDeadline(),
                entity.getName(),
                entity.getDescription(),
                entity.getTaskStatus()
        );
    }

    public static TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.id());
        entity.setDate(task.date());
        entity.setDeadline(task.deadline());
        entity.setName(task.name());
        entity.setDescription(task.description());
        entity.setTaskStatus(task.taskStatus());
        return entity;
    }
}
