package ru.otus.java.advanced.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.advanced.proto.CartID;
import ru.otus.advanced.proto.ProductID;
import ru.otus.advanced.proto.User;
import ru.otus.advanced.proto.UserID;
import ru.otus.java.advanced.clent.ProductClient;
import ru.otus.java.advanced.clent.UserClient;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class Emulator {

    private final ProductClient productClient;
    private final UserClient userClient;

    @Scheduled(fixedRate = 5000)
    public void emulate() {
        log.info("Creating user");
        UserID userId = userClient.createUser("username", "email@email.com");
        log.info("User with id {} created", userId);
        log.info("ChangeUserEmailt");
        User user = userClient.changeUserEmail(UUID.fromString(userId.getValue()), "newEmail@email.com");
        log.info("User with id {} changed: response {}", userId, user);
        log.info("ChangeUserName");
        user = userClient.changeUserName(UUID.fromString(userId.getValue()), "newUserName");
        log.info("User with id {} changed: response {}", userId, user);

        log.info("Creating product");
        ProductID productId = productClient.createProduct("product");
        log.info("Product with id {} created", productId);
        log.info("Add Product to cart");
        CartID cartID = productClient.addProductToCart(UUID.fromString(productId.getValue()), UUID.fromString(userId.getValue()));
        log.info("Cart with id {} added", cartID);

    }
}
