package org.example;

import java.util.HashMap;
import java.util.Map;

import com.example.grpc.ProductServiceGrpc;
import com.example.grpc.ProductServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private Map<String, ProductServiceOuterClass.Product> products = new HashMap<>();
    private Map<String, ProductServiceOuterClass.Product> userCart = new HashMap<>();

    @Override
    public void createProduct(ProductServiceOuterClass.Product request, StreamObserver<ProductServiceOuterClass.ProductResponse> responseObserver) {
        String id = String.valueOf(products.size() + 1);
        ProductServiceOuterClass.Product product = ProductServiceOuterClass.Product.newBuilder()
                .setId(id)
                .setName(request.getName())
                .build();
        products.put(id, product);

        ProductServiceOuterClass.ProductResponse response = ProductServiceOuterClass.ProductResponse.newBuilder()
                .setId(id)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addProductToCart(ProductServiceOuterClass.Product request, StreamObserver<ProductServiceOuterClass.Product> responseObserver) {
        String userId = getLoggedUserId();
        var product = products.get(request.getId());
        userCart.put(userId, product);

        ProductServiceOuterClass.Product response = ProductServiceOuterClass.Product.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String getLoggedUserId() {
        return "123";
    }
}
