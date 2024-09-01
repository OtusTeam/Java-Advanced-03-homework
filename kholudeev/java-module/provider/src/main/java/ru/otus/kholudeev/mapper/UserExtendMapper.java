package ru.otus.kholudeev.mapper;

import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.kholudeev.dao.service.UserExtend;

@Mapper(componentModel = "spring")
public interface UserExtendMapper {
    UserResponse toUserExtendResponse(UserExtend user);

    @Mapping(source = "errorCode", target = "error.code")
    @Mapping(source = "errorDescription", target = "error.description")
    UserResponse toUserResponseWithError(UserRequest userRequest, Integer errorCode, String errorDescription);

    UserExtend toExtendUser(UserRequest userRequest);
}
