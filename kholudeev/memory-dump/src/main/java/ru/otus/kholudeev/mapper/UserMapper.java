package ru.otus.kholudeev.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(source = "errorCode", target = "error.code")
    @Mapping(source = "errorDescription", target = "error.description")
    UserResponse toUserResponseWithError(UserRequest userRequest, Integer errorCode, String errorDescription);

    User toUser(UserRequest userRequest);
}
