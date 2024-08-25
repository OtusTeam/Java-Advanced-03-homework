package ru.otus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Gets 'Hello world!'", tags = "hello-word")
    public String hello() { return "Hello world!"; }
}
