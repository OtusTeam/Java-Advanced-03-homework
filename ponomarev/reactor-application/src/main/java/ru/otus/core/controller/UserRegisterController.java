package ru.otus.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.*;
import ru.otus.core.model.UserDto;
import ru.otus.core.model.UserEntity;
import ru.otus.core.service.UserService;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Mono<UserDto>> register(@RequestBody UserDto userDto) {
        var userEntity = userService.create(convertToEntity(userDto));
        log.info("Register success: " + userEntity);
        return ResponseEntity.ok(convertToDto(userEntity));
    }

    @GetMapping(path = "/getById/{id}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Mono<UserDto>> getById(@PathVariable String id) {
        var userEntity = userService.findUserById(id);
        log.info("getById: " + userEntity);
        return ResponseEntity.ok(convertToDto(userEntity));
    }

    @GetMapping(path = "/getAllUsers")
    @ResponseBody
    public ResponseEntity<Flux<UserDto>> getAllUsers() {
        var userEntities = userService.findAllUsers();
        log.info("getAllUsers: " + userEntities);
        return ResponseEntity.ok(userEntities.map(u->convertToDto(u)));
    }

    private UserEntity convertToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    private Mono<UserDto> convertToDto(Mono<UserEntity> userEntityMono) {
        return userEntityMono.map(u -> modelMapper.map(u, UserDto.class));
    }

    private UserDto convertToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
}
