package com.todolist.repositories;

import com.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
}
