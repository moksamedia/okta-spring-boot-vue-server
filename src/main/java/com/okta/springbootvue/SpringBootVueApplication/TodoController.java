package com.okta.springbootvue.SpringBootVueApplication;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    private TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos/completed")
    public Collection<Todo> todosCompleted() {
        return repository.findAll().stream()
                .filter(todo -> todo.getCompleted())
                .collect(Collectors.toList());
    }

    @GetMapping("/todos/uncompleted")
    public Collection<Todo> todosUncompleted() {
        return repository.findAll().stream()
                .filter(todo -> !todo.getCompleted())
                .collect(Collectors.toList());
    }
}
