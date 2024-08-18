package ru.otus.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.core.model.UserDto;

/**
 * @author Anton Ponomarev on 17.08.2024
 * @project Java-Advanced-homework
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class DockerController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world!";
    }
}
