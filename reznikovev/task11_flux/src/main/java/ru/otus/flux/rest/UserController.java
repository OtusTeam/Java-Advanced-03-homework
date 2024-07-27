package ru.otus.flux.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.flux.model.User;
import ru.otus.flux.model.UserDto;
import ru.otus.flux.model.UserMapper;
import ru.otus.flux.service.UserService;

@RestController
@RequestMapping("/api/user")
@Data
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Flux<UserDto> getAll() {
        return userService.getAll()
                .map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .defaultIfEmpty(responseNotFound());
    }

    @PostMapping
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return userService.delete(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(responseNotFound());
    }

    @PutMapping
    public Mono<ResponseEntity<UserDto>> update(@RequestBody UserDto user) {
        return userService.update(userMapper.toEntity(user))
                .map(userEntity -> ResponseEntity.ok(userMapper.toDto(userEntity)))
                .defaultIfEmpty(responseNotFound());
    }

    private static <T> ResponseEntity<T> responseNotFound() {
        return ResponseEntity.notFound().build();
    }
}
