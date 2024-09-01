package ru.otus.kholudeev.grpc.mapper;

import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.User;
import ru.otus.kholudeev.grpc.UserCreateRequest;
import ru.otus.kholudeev.grpc.dao.model.DaoUser;

@Service
public class UserMapper {
    public DaoUser toUser(UserCreateRequest request) {
        return DaoUser.builder()
                .name(request.getUsername())
                .email(request.getEmail())
                .build();
    }

    public User toResponse (DaoUser user) {
        return User.newBuilder()
                .setId(user.getId())
                .setUsername(user.getName())
                .setEmail(user.getEmail())
                .build();
    }
}
