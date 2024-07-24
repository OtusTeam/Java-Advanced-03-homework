package ru.otus.java.advanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.java.advanced.dto.UserDto;
import ru.otus.java.advanced.service.UserRegisterService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRegisterService userRegisterService;

    @PostMapping(value = "user/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDto>> register(@RequestBody UserDto userDto) {
        return userRegisterService.register(userDto)
                .map(ResponseEntity::ok);
    }
}
