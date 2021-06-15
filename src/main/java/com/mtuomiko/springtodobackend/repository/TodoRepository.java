package com.mtuomiko.springtodobackend.repository;

import com.mtuomiko.springtodobackend.Account;
import com.mtuomiko.springtodobackend.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByAccount(Account account);
}
