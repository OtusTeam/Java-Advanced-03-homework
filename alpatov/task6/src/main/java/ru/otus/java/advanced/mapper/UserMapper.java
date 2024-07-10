package ru.otus.java.advanced.mapper;

import org.mapstruct.Mapper;
import ru.otus.java.advanced.dto.UserDto;
import ru.otus.java.advanced.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto model);

    UserDto toDto(User user);
}
