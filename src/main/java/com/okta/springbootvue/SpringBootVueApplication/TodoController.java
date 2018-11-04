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

    @PostMapping("/todo")
    public String addTodo(@RequestBody String text) {
        Todo todo = new Todo();
        todo.setText(text);
        repository.save(todo);
        return "success";
    }

    @PutMapping("/todo/{id}")
    public String updateTodo(@PathVariable Long id, @RequestBody String text, @RequestBody Boolean completed) {
        Todo todo = repository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found for id"));
        todo.setText(text);
        todo.setCompleted(completed);
        return "success";
    }

    @GetMapping("/todo/{id}")
    public Todo getTodoForId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found for id"));
    }
    
    @GetMapping("/todo")
    public Collection<Todo> getAllTodos() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/todo/completed")
    public Collection<Todo> todosCompleted() {
        return repository.findAll().stream()
                .filter(todo -> todo.getCompleted())
                .collect(Collectors.toList());
    }

    @GetMapping("/todo/uncompleted")
    public Collection<Todo> todosUncompleted() {
        return repository.findAll().stream()
                .filter(todo -> !todo.getCompleted())
                .collect(Collectors.toList());
    }
}
