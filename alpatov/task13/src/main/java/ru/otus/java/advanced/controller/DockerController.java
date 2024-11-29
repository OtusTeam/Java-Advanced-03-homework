package ru.otus.java.advanced.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    @GetMapping("hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World");
    }
}
