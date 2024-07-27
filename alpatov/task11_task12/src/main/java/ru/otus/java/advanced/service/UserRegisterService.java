package ru.otus.java.advanced.service;

import reactor.core.publisher.Mono;
import ru.otus.java.advanced.dto.UserDto;

public interface UserRegisterService {

    Mono<UserDto> register(UserDto userDto);
}
