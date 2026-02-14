package com.szymaniaq.todo.todoapp.service;

import com.szymaniaq.todo.todoapp.model.Task;
import com.szymaniaq.todo.todoapp.model.TaskStatus;
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
                .map(TaskConverter::toModel)
                .toList();
    }

    public Task save(Task task) {
        return TaskConverter.toModel(taskRepository.save(TaskConverter.toEntity(task)));
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
                .map(TaskConverter::toModel)
                .orElse(null);
    }


}