package com.mtuomiko.springtodobackend;

import com.mtuomiko.springtodobackend.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void initTestUser() {
        Account account = new Account();
        account.setUsername("testUser");
        account.setPassword("imaginaryPassHash");
        accountRepository.save(account);
    }

    @AfterEach
    public void clearRepository() {
        accountRepository.deleteAll();
    }

    @Test
    public void create_validRequest_shouldCreateAccount() throws Exception {
        RegisterRequest validRequest = new RegisterRequest();
        validRequest.setUsername("newUser");
        validRequest.setPassword("someHash");

        customUserDetailsService.create(validRequest);

        Account foundAccount = accountRepository.findByUsername(validRequest.getUsername());
        assertEquals(validRequest.getUsername(), foundAccount.getUsername(), "did not find created user");
    }

    @Test
    public void create_existingUsername_shouldThrowValidationException() throws Exception {
        RegisterRequest badRequest = new RegisterRequest();
        badRequest.setUsername("testUser");
        badRequest.setPassword("whatever");

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                customUserDetailsService.create(badRequest)
        );
        assertEquals("username already exists", validationException.getMessage(), "validation error message does not match");
    }

}
