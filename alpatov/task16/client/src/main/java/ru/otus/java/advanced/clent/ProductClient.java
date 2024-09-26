package ru.otus.java.advanced.clent;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import ru.otus.advanced.proto.*;

import java.util.UUID;

@Component
public class ProductClient {

    @GrpcClient(value = "product-client")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public ProductID createProduct(String name) {
        return productServiceBlockingStub.createProduct(
                CreateProductRequest
                        .newBuilder()
                        .setName(name)
                        .build());
    }

    public CartID addProductToCart(UUID productId, UUID userId) {
        return productServiceBlockingStub.addProductToCart(
                ProductToCartRequest
                        .newBuilder()
                        .setProductId(productId.toString())
                        .setUserId(userId.toString())
                        .build());
    }

}
