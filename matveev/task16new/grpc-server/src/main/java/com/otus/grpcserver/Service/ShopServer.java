package com.otus.grpcserver.Service;

import com.otus.grpc.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devh.boot.grpc.server.service.GrpcService;
import java.util.HashMap;
import java.util.Map;


@GrpcService
public class ShopServer extends ProductInfoGrpc.ProductInfoImplBase {
    private static final Logger logger = LoggerFactory.getLogger(ShopServer.class);

    private Map<String, User> usersMap = new HashMap<>();
    private Map<String, Product> productMap = new HashMap<>();
    private Map<User, Product> orderMap = new HashMap<>();


    @Override
    public void createUser(User request, StreamObserver<UserID> responseObserver) {
        UserID setResponseMessage = UserID.newBuilder()
                .setValue(String.format("User created: id: %s, userName: %s,email: %s ",
                        request.getId(), request.getUsername(), request.getEmail())).build();
        usersMap.put(request.getId(), request);
        logger.info(String.format("User was created: {%s, %s, %s}", request.getId(), request.getUsername(), request.getEmail()));
        responseObserver.onNext(setResponseMessage);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserEmail(User request, StreamObserver<User> responseObserver) {
        User user = usersMap.get(request.getId());
        User updatedUser = User.newBuilder().setId(user.getId()).setUsername(user.getUsername()).setEmail(request.getEmail()).build();
        usersMap.put(user.getId(), updatedUser);
        User setResponseMessage = User.newBuilder()
                .setEmail(String.format("Changed email for user %s : %s -> %s ", user.getId(), user.getEmail(), request.getEmail())).build();
        logger.info(String.format("Email was updated: {%s, %s, %s}", updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail()));
        responseObserver.onNext(setResponseMessage);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserName(User request, StreamObserver<User> responseObserver) {
        User user = usersMap.get(request.getId());
        User updatedUser = User.newBuilder().setId(user.getId()).setUsername(request.getUsername()).setEmail(user.getEmail()).build();
        usersMap.put(user.getId(), updatedUser);
        User setResponseMessage = User.newBuilder()
                .setEmail(String.format("Changed userName for user %s : %s -> %s ", updatedUser.getId(), user.getUsername(), updatedUser.getUsername())).build();
        logger.info(String.format("UserName was updated: {%s, %s, %s}", updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail()));
        responseObserver.onNext(setResponseMessage);
        responseObserver.onCompleted();
    }

    public void createProduct(Product product, StreamObserver<ProductID> productIDStreamObserver) {
        ProductID productID = ProductID.newBuilder().setValue(product.getId()).build();
        productMap.put(product.getId(), product);
        logger.info(String.format("Product was added: {%s, %s}", product.getId(), product.getName()));
        productIDStreamObserver.onNext(productID);
        productIDStreamObserver.onCompleted();
    }

    public void addProductToCart(Card card, StreamObserver<Card> productIDStreamObserver) {
        User currentUser = usersMap.get(card.getUserId());
        Product product = productMap.get(card.getProductId());
        Card currentCard = Card.newBuilder().setProductId(card.getProductId()).setUserId(card.getUserId()).build();
        orderMap.put(currentUser, product);
        logger.info(String.format("Product %s was added to card for user: %s.", card.getUserId(), card.getProductId()));
        productIDStreamObserver.onNext(currentCard);
        productIDStreamObserver.onCompleted();
    }

}