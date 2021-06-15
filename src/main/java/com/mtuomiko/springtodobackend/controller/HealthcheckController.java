package com.mtuomiko.springtodobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {
    @GetMapping("/health")
    public String healthcheck() {
        return "ok";
    }

    @GetMapping("/")
    public void rootResponse() {
    }
}
