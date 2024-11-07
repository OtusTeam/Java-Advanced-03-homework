package ru.otus.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.User;
import ru.otus.model.UserData;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserClientResilienceAdapter userClientResilienceAdapter;


    @GetMapping("/")
    @Operation(summary = "Gets all users", tags = "user")
    public Flux<String> getAll() {
        return userClientResilienceAdapter.getAll();
    }

    @GetMapping("/{login}")
    @Operation(summary = "Gets user by login", tags = "user")
    public Mono<String> getUser(@PathVariable String login) {
        return userClientResilienceAdapter.findByLogin(login);
    }

    @GetMapping("/{login}/age")
    @Operation(summary = "Gets user age", tags = "user")
    public Mono<Long> getUserAge(@PathVariable String login) {
        return userClientResilienceAdapter.getUserAge(login);
    }

    @GetMapping("/report")
    @Operation(summary = "Gets user monitoring report", tags = "user")
    public Flux<UserData> getUserReport() {
        return userClientResilienceAdapter.getUserReport();
    }

    @PostMapping
    @Operation(summary = "Saves new user", tags = "user")
    public Mono<String> saveUser(@RequestBody User user) {
        return userClientResilienceAdapter.save(user);
    }

    @PutMapping
    @Operation(summary = "Updates user", tags = "user")
    public Mono<String> updateUser(@RequestBody User user) {
        return userClientResilienceAdapter.update(user);
    }

    @DeleteMapping("/{login}")
    @Operation(summary = "Deletes user", tags = "user")
    public Mono<String> deleteUser(@PathVariable String login) {
        return userClientResilienceAdapter.delete(login);
    }
}
