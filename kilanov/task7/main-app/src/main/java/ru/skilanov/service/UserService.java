package ru.skilanov.service;

import ru.skilanov.dto.UserCreateDto;
import ru.skilanov.dto.UserDto;

public interface UserService {

    UserDto create(UserCreateDto dto);

}
