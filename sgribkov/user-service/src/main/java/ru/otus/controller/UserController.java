package ru.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.service.UserService;
import ru.otus.model.User;
import ru.otus.model.UserData;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public Flux<String> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{login}")
    public Mono<String> getUser(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @GetMapping("/report")
    public Flux<UserData> getUserReport() {
        return userService.getUserReport();
    }

    @PostMapping
    public Mono<String> saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public Mono<String> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{login}")
    public Mono<String> deleteUser(@PathVariable String login) {
        return userService.delete(login);
    }
}
