package ru.otus.java.advanced.clent;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import ru.otus.advanced.proto.*;

import java.util.UUID;

@Component
public class UserClient {

    @GrpcClient(value = "user-client")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public UserID createUser(String username, String email) {
        return userServiceStub.createUser(
                CreateUserRequest
                        .newBuilder()
                        .setUsername(username)
                        .setEmail(email)
                        .build()
        );
    }

    public User changeUserEmail(UUID userId, String email){
        return userServiceStub.changeUserEmail(
                ChangeUserEmailRequest
                        .newBuilder()
                        .setId(userId.toString())
                        .setEmail(email)
                        .build());
    }

    public User changeUserName(UUID userId, String userName){
        return userServiceStub.changeUserName(
                ChangeUserNameRequest
                        .newBuilder()
                        .setId(userId.toString())
                        .setUsername(userName)
                        .build());
    }
}
