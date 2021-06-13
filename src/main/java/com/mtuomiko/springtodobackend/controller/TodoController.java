package com.mtuomiko.springtodobackend.controller;

import com.mtuomiko.springtodobackend.Account;
import com.mtuomiko.springtodobackend.AccountRepository;
import com.mtuomiko.springtodobackend.Todo;
import com.mtuomiko.springtodobackend.TodoRepository;
import com.mtuomiko.springtodobackend.exception.NotAuthorizedException;
import com.mtuomiko.springtodobackend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/accounts/{username}/todos")
    public List<Todo> getTodosByUsername(@PathVariable String username, Principal principal) {
        Account account = accountRepository.findByUsername(principal.getName());
        if (!account.getUsername().equals(username)) {
            throw new NotAuthorizedException("forbidden");
        }
        return todoRepository.findByAccount(account);
    }

    @PostMapping("/accounts/{username}/todos")
    public Todo addTodo(@PathVariable String username, Principal principal, @RequestBody @Valid Todo todo) {
        Account account = accountRepository.findByUsername(principal.getName());
        if (!account.getUsername().equals(username)) {
            throw new NotAuthorizedException("forbidden");
        }
        todo.setAccount(account);
        return todoRepository.save(todo);
    }

    @PutMapping("/accounts/{username}/todos/{todoId}")
    public Todo updateTodo(
            @PathVariable String username,
            @PathVariable Long todoId,
            Principal principal,
            @RequestBody @Valid Todo newTodo
    ) {
        Account account = accountRepository.findByUsername(principal.getName());
        if (!account.getUsername().equals(username)) {
            throw new NotAuthorizedException("forbidden");
        }
        return todoRepository.findById(todoId)
                .map(todo -> {
                    todo.setContent(newTodo.getContent());
                    todo.setDone(newTodo.getDone());
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new NotFoundException(String.format("todo with id %s not found", todoId)));
    }

}
