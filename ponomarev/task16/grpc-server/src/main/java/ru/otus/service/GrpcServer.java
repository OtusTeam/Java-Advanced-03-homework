package ru.otus.service;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import ru.otus.grpc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Anton Ponomarev on 08.09.2024
 * @project Java-Advanced-homework
 */
@GrpcService
public class GrpcServer extends ProductServiceGrpc.ProductServiceImplBase {
    private static Map<String, User> userMap = new HashMap<>();
    private static Map<String, Product> productMap = new HashMap<>();
    private static Map<String, Cart> cartMap = new HashMap<>();

    @Override
    public void createUser(User request, StreamObserver<UserID> responseObserver) {
        String randomUUIDString = String.valueOf(UUID.randomUUID());

        User toSave = request
                .toBuilder()
                .setId(randomUUIDString)
                .build();
        userMap.put(randomUUIDString, toSave);


        UserID id = UserID
                .newBuilder()
                .setValue(randomUUIDString)
                .build();

        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public void findByID(UserID request, StreamObserver<User> responseObserver) {
        String id = request.getValue();

        if (userMap.containsKey(id)) {
            responseObserver.onNext(userMap.get(id));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

    @Override
    public void changeUserEmail(User request, StreamObserver<User> responseObserver) {

        String id = request.getId();

        if (userMap.containsKey(id)) {
            User user = userMap.get(id).toBuilder()
                    .setEmail(request.getEmail())
                    .build();

            userMap.put(id, user);
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

    @Override
    public void changeUserName(User request, StreamObserver<User> responseObserver) {
        String id = request.getId();

        if (userMap.containsKey(id)) {
            User user = userMap.get(id).toBuilder()
                    .setUsername(request.getUsername())
                    .build();

            userMap.put(id, user);
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

    @Override
    public void createProduct(Product request, StreamObserver<ProductID> responseObserver) {
        String randomUUIDString = String.valueOf(UUID.randomUUID());

        Product toSave = request
                .toBuilder()
                .setId(randomUUIDString)
                .build();
        productMap.put(randomUUIDString, toSave);

        ProductID id = ProductID
                .newBuilder()
                .setValue(randomUUIDString)
                .build();

        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductID request, StreamObserver<Product> responseObserver) {
        String id = request.getValue();

        if (productMap.containsKey(id)) {
            responseObserver.onNext((Product) productMap.get(id));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

    @Override
    public void addProductToCart(Cart request, StreamObserver<Cart> responseObserver) {
        cartMap.put(request.getUserId(), request);

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}
