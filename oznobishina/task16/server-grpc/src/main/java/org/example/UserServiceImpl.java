package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.grpc.UserServiceGrpc;
import com.example.grpc.UserServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private Set<UserServiceOuterClass.User> users = new HashSet<>();

    @Override
    public void createUser(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.UserResponse> responseObserver) {
        String id = String.valueOf(users.size() + 1);
        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(id)
                .setUserName(request.getUserName())
                .setEmail(request.getEmail())
                .build();
        users.add(user);

        UserServiceOuterClass.UserResponse response = UserServiceOuterClass.UserResponse.newBuilder()
                .setId(id)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserEmail(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        UserServiceOuterClass.User existingUser = users.stream()
                .filter(user -> Objects.equals(user.getId(), request.getId()))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(existingUser.getId())
                .setEmail(request.getEmail())
                .setUserName(existingUser.getUserName())
                .build();
        users.add(user);

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserName(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        UserServiceOuterClass.User existingUser = users.stream()
                .filter(user -> Objects.equals(user.getId(), request.getId()))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);

        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(existingUser.getId())
                .setEmail(existingUser.getEmail())
                .setUserName(request.getUserName())
                .build();
        users.add(user);

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
