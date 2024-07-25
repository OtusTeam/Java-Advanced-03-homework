package ru.otus.kholudeev.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
public class HelloController {
    @RequestMapping("/")
    public String hello()
    {
        return "Hello world";
    }
}
