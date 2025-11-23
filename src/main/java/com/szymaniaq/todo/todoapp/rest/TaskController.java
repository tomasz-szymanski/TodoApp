package com.szymaniaq.todo.todoapp.rest;

import com.szymaniaq.todo.todoapp.model.Task;
import com.szymaniaq.todo.todoapp.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/todo")
    public Iterable<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping("/todo")
    public Task addTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/todo/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable Long id) {
        Task updated = taskService.completeTask(id);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/todo/{id}/open")
    public ResponseEntity<Task> open(@PathVariable Long id) {
        Task updated = taskService.openTask(id);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
