package com.mtuomiko.springtodobackend.repository;

import com.mtuomiko.springtodobackend.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}