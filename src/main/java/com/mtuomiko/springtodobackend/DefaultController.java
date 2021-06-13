package com.mtuomiko.springtodobackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/health")
    public String healthcheck() {
        return "ok";
    }
}
