package ru.otus.java.advanced.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.otus.advanced.proto.*;

import java.util.*;

@GrpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

    private final Map<UUID, Product> productRepository = new HashMap<>();
    private final Map<UUID, Cart> cartRepository = new HashMap<>();

    @Data
    @Builder
    public static class Product {
        private UUID id;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Cart {
        private UUID id;
        private List<UUID> products = new ArrayList<>();
    }


    @Override
    public void createProduct(CreateProductRequest request, StreamObserver<ProductID> responseObserver) {
        UUID id = UUID.randomUUID();
        productRepository.put(id, Product.builder().id(id).name(request.getName()).build());
        responseObserver.onNext(
                ProductID.newBuilder()
                        .setValue(id.toString())
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void addProductToCart(ProductToCartRequest request, StreamObserver<CartID> responseObserver) {
        String productId = request.getProductId();
        String userId = request.getUserId();
        if (!cartRepository.containsKey(UUID.fromString(userId))) {
            UUID id = UUID.randomUUID();
            Cart cart = new Cart(id, new ArrayList<>());
            cart.getProducts().add(UUID.fromString(productId));
            cartRepository.put(UUID.fromString(userId), cart);
        } else {
            Cart cart = cartRepository.get(UUID.fromString(userId));
            cart.getProducts().add(UUID.fromString(productId));
        }

        responseObserver.onNext(
                CartID.newBuilder()
                        .setValue(cartRepository.get(UUID.fromString(userId)).getId().toString())
                        .build());
        responseObserver.onCompleted();

    }
}
