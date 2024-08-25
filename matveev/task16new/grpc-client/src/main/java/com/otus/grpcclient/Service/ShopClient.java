package com.otus.grpcclient.Service;

import com.otus.grpc.*;
import com.otus.grpc.ProductInfoGrpc.ProductInfoBlockingStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class ShopClient {
    private static final Logger logger = LoggerFactory.getLogger(ShopClient.class);

    @GrpcClient("grpc-server")
    private ProductInfoBlockingStub productInfoBlockingStub;

    public void createUser(String id, String name, String email) {
        logger.info(String.format("Send request createUser - userId: %s, name: %s, email: %s", id, name, email));
        User product = User.newBuilder().setId(id).setUsername(name).setEmail(email).build();
        UserID createProductResponse = this.productInfoBlockingStub.createUser(product);
        logger.info(String.format("Server response: {%s} ", createProductResponse.getValue()));
    }

    public void changeUserEmail(String id, String email) {
        logger.info(String.format("Send request changeUserEmail - userId: %s, email: %s", id, email));
        User user = User.newBuilder().setId(id).setEmail(email).build();
        User createProductResponse = this.productInfoBlockingStub.changeUserEmail(user);
        logger.info(String.format("Server response: {%s} ", createProductResponse));
    }

    public void changeUserName(String id, String username) {
        logger.info(String.format("Send request changeUserName - userID: %s, username: %s", id, username));
        User user = User.newBuilder().setId(id).setUsername(username).build();
        User createProductResponse = this.productInfoBlockingStub.changeUserName(user);
        logger.info(String.format("Server response: {%s}", createProductResponse));
    }

    public void createProduct(String productId, String description) {
        logger.info(String.format("Send request createProduct - productId: %s, name: %s", productId, description));
        Product product = Product.newBuilder().setId(productId).setName(description).build();
        ProductID createProductResponse = this.productInfoBlockingStub.createProduct(product);
        logger.info(String.format("Server response: {%s}", createProductResponse));
    }

    public void addProductToCard(String userId, String productId) {
        logger.info(String.format("Send request addProductToCard - userId: %s, productId: %s", userId, productId));
        Card card = Card.newBuilder().setUserId(userId).setProductId(productId).build();
        Card createProductResponse = this.productInfoBlockingStub.addProductToCart(card);
        logger.info(String.format("Server response: userId: %s, productId %s:", createProductResponse.getUserId(), createProductResponse.getProductId()));
    }

}