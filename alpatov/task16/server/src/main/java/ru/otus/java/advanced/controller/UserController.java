package ru.otus.java.advanced.controller;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.otus.advanced.proto.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@GrpcService
@Slf4j
public class UserController extends UserServiceGrpc.UserServiceImplBase {

    @Data
    @Builder
    public static class User {
        private UUID id;
        private String userName;
        private String email;
    }

    private final Map<UUID, User> userRepository = new HashMap<>();

    @Override
    public void changeUserEmail(ChangeUserEmailRequest request, StreamObserver<ru.otus.advanced.proto.User> responseObserver) {
        if (!userRepository.containsKey(UUID.fromString(request.getId()))) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
        userRepository.get(UUID.fromString(request.getId())).setEmail(request.getEmail());
        User user = userRepository.get(UUID.fromString(request.getId()));
        responseObserver.onNext(
                ru.otus.advanced.proto.User
                        .newBuilder()
                        .setId(request.getId())
                        .setEmail(user.getEmail())
                        .setUsername(user.getUserName())
                        .build()
        );
        responseObserver.onCompleted();

    }

    @Override
    public void changeUserName(ChangeUserNameRequest request, StreamObserver<ru.otus.advanced.proto.User> responseObserver) {
        if (!userRepository.containsKey(UUID.fromString(request.getId()))) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
        userRepository.get(UUID.fromString(request.getId())).setUserName(request.getUsername());
        User user = userRepository.get(UUID.fromString(request.getId()));
        responseObserver.onNext(
                ru.otus.advanced.proto.User
                        .newBuilder()
                        .setId(request.getId())
                        .setEmail(user.getEmail())
                        .setUsername(user.getUserName())
                        .build()
                );
        responseObserver.onCompleted();

    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserID> responseObserver) {
        log.info(request.toString());
        UUID id = UUID.randomUUID();
        User user = User
                .builder()
                .id(id)
                .userName(request.getUsername())
                .email(request.getEmail())
                .build();
        userRepository.put(id, user);
        responseObserver.onNext(UserID
                .newBuilder()
                .setValue(id.toString())
                .build());
        responseObserver.onCompleted();

    }

}
