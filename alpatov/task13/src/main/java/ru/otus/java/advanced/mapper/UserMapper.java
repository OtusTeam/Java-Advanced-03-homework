package ru.otus.java.advanced.mapper;

import org.springframework.stereotype.Component;
import ru.otus.java.advanced.dto.UserDto;
import ru.otus.java.advanced.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setLogin(userDto.getLogin());
        return user;
    }

     public UserDto toDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
