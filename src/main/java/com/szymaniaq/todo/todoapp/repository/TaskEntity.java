package com.szymaniaq.todo.todoapp.repository;

import com.szymaniaq.todo.todoapp.model.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalDateTime deadline;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus taskStatus;

}
