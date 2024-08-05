package ru.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String hello() { return "Hello world!"; }
}
