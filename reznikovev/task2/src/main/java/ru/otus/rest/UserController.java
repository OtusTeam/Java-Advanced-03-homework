package ru.otus.rest;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.User;
import ru.otus.model.UserDto;
import ru.otus.model.UserMapper;
import ru.otus.service.UserService;

@RestController
@RequestMapping("/api/user")
@Data
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        return userMapper.toDto(userService.create(userMapper.toEntity(user)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto user) {
        return userMapper.toDto(userService.update(userMapper.toEntity(user)));
    }
}
