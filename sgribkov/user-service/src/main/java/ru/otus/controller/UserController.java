package ru.otus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Gets all users", tags = "user")
    public Flux<String> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{login}")
    @Operation(summary = "Gets user by login", tags = "user")
    public Mono<String> getUser(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @GetMapping("/report")
    @Operation(summary = "Gets user monitoring report", tags = "user")
    public Flux<UserData> getUserReport() {
        return userService.getUserReport();
    }

    @PostMapping
    @Operation(summary = "Saves new user", tags = "user")
    public Mono<String> saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    @Operation(summary = "Updates user", tags = "user")
    public Mono<String> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{login}")
    @Operation(summary = "Deletes user", tags = "user")
    public Mono<String> deleteUser(@PathVariable String login) {
        return userService.delete(login);
    }
}
