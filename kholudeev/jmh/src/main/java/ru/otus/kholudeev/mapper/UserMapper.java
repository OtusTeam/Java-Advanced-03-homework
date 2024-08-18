package ru.otus.kholudeev.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.service.PasswordHashingService;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(source = "errorCode", target = "error.code")
    @Mapping(source = "errorDescription", target = "error.description")
    UserResponse toUserResponseWithError(UserRequest userRequest, Integer errorCode, String errorDescription);

    default User toUser(UserRequest userRequest, PasswordHashingService passwordHashingService, String algorithm) {
        return User.builder()
                .login(userRequest.getLogin())
                .name(userRequest.getName())
                .password(passwordHashingService.getHashedPassword(userRequest.getPassword(), algorithm))
                .build();
    }
}
