package com.szymaniaq.todo.todoapp.service;

import com.szymaniaq.todo.todoapp.model.Task;
import com.szymaniaq.todo.todoapp.model.TaskStatus;
import com.szymaniaq.todo.todoapp.repository.TaskEntity;
import com.szymaniaq.todo.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Iterable<Task> findAll() {
        var entities = taskRepository.findAllOrderByIdDesc();
        return entities.stream()
                .map(this::toModel)
                .toList();
    }

    public Task save(Task task) {
        return toModel(taskRepository.save(toEntity(task)));
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task completeTask(Long id) {
        return updateStatus(id, TaskStatus.CLOSED);
    }

    public Task openTask(Long id) {
        return updateStatus(id, TaskStatus.OPEN);
    }

    public Task updateStatus(Long id, TaskStatus status) {
        return taskRepository.findById(id)
                .map(entity -> {
                    entity.setTaskStatus(status);
                    return taskRepository.save(entity);
                })
                .map(this::toModel)
                .orElse(null);
    }

    // ... existing code ...

    private Task toModel(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getDate(),
                entity.getDeadline(),
                entity.getName(),
                entity.getDescription(),
                entity.getTaskStatus()
        );
    }

    private TaskEntity toEntity(Task task) {
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