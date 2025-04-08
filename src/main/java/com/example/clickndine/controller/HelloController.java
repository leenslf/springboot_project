package com.example.clickndine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller to test the backend.
 */
@RestController
public class HelloController {

    /**
     * Returns a simple greeting message.
     * Access this endpoint at http://localhost:8080/hello
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Click & Dine Backend!";
    }
}
