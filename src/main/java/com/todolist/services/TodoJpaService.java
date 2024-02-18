package com.todolist.services;

import com.todolist.models.Todo;
import com.todolist.repositories.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoJpaService {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    public List<Todo> getTodos() {
        return todoJpaRepository.findAll();
    }

    public Todo getTodo(int id) throws ResponseStatusException {
        Optional<Todo> optionalTodo = todoJpaRepository.findById(id);
        return optionalTodo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Todo saveTodo(Todo todo) {
        todo.setStatus(todo.getStatus().toUpperCase());
        todo.setPriority(todo.getPriority().toUpperCase());
        return todoJpaRepository.save(todo);
    }

    public Todo updateTodo(int id, Todo todo) throws ResponseStatusException {
        Optional<Todo> optionalTodo = todoJpaRepository.findById(id);
        Todo todoToModify = optionalTodo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (todo.getTodo() != null) {
            todoToModify.setTodo(todo.getTodo());
        }
        if (todo.getStatus() != null) {
            todoToModify.setStatus(todo.getStatus());
        }
        if (todo.getPriority() != null) {
            todoToModify.setPriority(todo.getPriority());
        }
        return todoJpaRepository.save(todoToModify);
    }

    public void deleteTodo(int id) throws ResponseStatusException {
        Optional<Todo> optionalTodo = todoJpaRepository.findById(id);
        Todo todoToModify = optionalTodo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        todoJpaRepository.deleteById(id);
    }
}
