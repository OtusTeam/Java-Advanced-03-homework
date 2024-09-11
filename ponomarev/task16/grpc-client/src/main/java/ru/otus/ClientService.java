package ru.otus;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.grpc.*;

import java.util.UUID;

/**
 * @author Anton Ponomarev on 07.09.2024
 * @project Java-Advanced-homework
 */
@Service
@RequiredArgsConstructor
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @GrpcClient("grpc-server")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;
    public ProductID createProduct(String productName) {

        var productID = productServiceBlockingStub
                .createProduct(
                        Product
                                .newBuilder()
                                .setName(productName)
                                .build()
                );
        logger.info("Product ID: " + productID.getValue() + " added successfully.");

        var product = productServiceBlockingStub.getProduct(productID);
        logger.info("Product: " + product.toString());
//    channel.shutdown();
        return productID;
    }

    public UserID createUser(String userName, String email) {
        User user = User.newBuilder()
                .setEmail(email)
                .setUsername(userName)
                .build();
        logger.info("Creating user: {}", user);

        UserID userID = productServiceBlockingStub.createUser(user);
        logger.info("Created successfully user with userID {}", userID);
        return userID;
    }

    public User changeUserEmail(String id, String newUserEmail) {
        User user = User.newBuilder()
                .setId(id)
                .setEmail(newUserEmail)
                .build();
        logger.info("Updating user: {}", user);
        User userRes = productServiceBlockingStub.changeUserEmail(user);
        logger.info("updated successfully user email {}", userRes);
        return userRes;
    }

    public User changeUserName(String id, String newUserName) {
        User user = User.newBuilder()
                .setId(id)
                .setUsername(newUserName)
                .build();
        logger.info("Updating user: {}", user);
        User userRes = productServiceBlockingStub.changeUserName(user);
        logger.info("updated successfully user name {}", userRes);
        return userRes;
    }

    public Cart addProductToCart(String userId, String productId) {

        Cart cart = Cart.newBuilder()
                .setUserId(userId)
                .setProductId(productId)
                .build();
        logger.info("Creating cart: {}", cart);
        var cartRs = productServiceBlockingStub.addProductToCart(cart);
        logger.info("created successfully cart {}", cartRs);
        return cartRs;
    }

}
