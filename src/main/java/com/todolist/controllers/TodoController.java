package com.todolist.controllers;

import com.todolist.models.Todo;
import com.todolist.services.TodoJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoJpaService todoJpaService;

    @GetMapping(path = "/todos")
    public ResponseEntity<List<Todo>> viewTodos() {
        return ResponseEntity.ok(todoJpaService.getTodos());
    }

    @PostMapping(path = "/todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoJpaService.saveTodo(todo));
    }

    @GetMapping(path = "/todos/{id}")
    public ResponseEntity<?> viewTodo(@PathVariable int id) {
        try {
            Todo todo = todoJpaService.getTodo(id);
            return ResponseEntity.ok(todo);
        } catch (ResponseStatusException responseStatusException) {
            return ResponseEntity.status(responseStatusException.getStatusCode())
                           .body("Read unsuccessful: Todo not found with id: " + id);
        }
    }

    @PutMapping(path = "/todos/{id}")
    public ResponseEntity<?> modifyTodo(@PathVariable int id, @RequestBody Todo todo) {
        try {
            Todo todoToModify = todoJpaService.updateTodo(id, todo);
            return ResponseEntity.ok(todoToModify);
        } catch (ResponseStatusException responseStatusException) {
            return ResponseEntity.status(responseStatusException.getStatusCode())
                           .body("Update unsuccessful: Todo not found with id: " + id);
        }
    }

    @DeleteMapping(path = "/todos/{id}")
    public ResponseEntity<?> removeTodo(@PathVariable int id) {
        try {
            todoJpaService.deleteTodo(id);
            return ResponseEntity.ok("Todo with id: " + id + " deleted");
        } catch (ResponseStatusException responseStatusException) {
            return ResponseEntity.status(responseStatusException.getStatusCode())
                           .body("Delete unsuccessful: Todo not found with id: " + id);
        }
    }


}
