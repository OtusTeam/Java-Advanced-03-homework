package ru.otus.kholudeev.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.facade.UserFacade;

@RestController
@AllArgsConstructor
@RequestMapping("/authorization")
public class UserController {
    private final UserFacade facade;

    @PostMapping("/user")
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return facade.create(request);
    }

    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable @NotNull Long id) {
        return facade.getById(id);
    }
}
