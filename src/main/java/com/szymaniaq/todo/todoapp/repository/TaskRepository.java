package com.szymaniaq.todo.todoapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
    
    @Query("select t from TaskEntity t order by t.id desc")
    List<TaskEntity> findAllOrderByIdDesc();
    
}
