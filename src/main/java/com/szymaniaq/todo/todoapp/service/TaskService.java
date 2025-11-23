package com.szymaniaq.todo.todoapp.service;

import com.szymaniaq.todo.todoapp.model.Task;
import com.szymaniaq.todo.todoapp.model.TaskStatus;
import com.szymaniaq.todo.todoapp.repository.TaskEntity;
import com.szymaniaq.todo.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Iterable<Task> findAll() {
        Iterable<TaskEntity> all = taskRepository.findAllOrderByIdDesc();
        return StreamSupport.stream(all.spliterator(), false)
                .map(this::toModel)
                .toList();
    }

    public Task save(Task task) {
        TaskEntity entity = toEntity(task);
        TaskEntity saved = taskRepository.save(entity);
        return toModel(saved);
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
        Optional<TaskEntity> optional = taskRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        TaskEntity entity = optional.get();
        entity.setTaskStatus(status);
        TaskEntity saved = taskRepository.save(entity);
        return toModel(saved);
    }


    private Task toModel(TaskEntity e) {
        return new Task(e.getId(), e.getDate(), e.getName(), e.getDescription(), e.getTaskStatus());
    }

    private TaskEntity toEntity(Task task) {
        TaskEntity e = new TaskEntity();
        e.setId(task.id());
        e.setDate(task.date());
        e.setName(task.name());
        e.setDescription(task.description());
        e.setTaskStatus(task.taskStatus());
        return e;
    }
}
