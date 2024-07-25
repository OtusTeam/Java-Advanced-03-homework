package ru.otus.java.advanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.java.advanced.entity.User;
import ru.otus.java.advanced.service.UserRegisterService;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRegisterService userRegisterService;

    @PostMapping(value = "user/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userRegisterService.register(user));
    }
}
