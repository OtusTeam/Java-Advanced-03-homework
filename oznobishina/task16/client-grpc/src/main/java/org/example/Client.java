package org.example;

import com.example.grpc.ProductServiceGrpc;
import com.example.grpc.ProductServiceOuterClass;
import com.example.grpc.UserServiceGrpc;
import com.example.grpc.UserServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        //user
        UserServiceGrpc.UserServiceBlockingStub userStub = UserServiceGrpc.newBlockingStub(channel);
        UserServiceOuterClass.User user = UserServiceOuterClass.User
                .newBuilder()
                .setUserName("Albert")
                .setEmail("AlbertEinstein@mail.com")
                .build();
        UserServiceOuterClass.UserResponse userResponse = userStub.createUser(user);
        System.out.println("Created user: \n" + userResponse);

        UserServiceOuterClass.User userToChangeEmail = UserServiceOuterClass.User
                .newBuilder()
                .setId(userResponse.getId())
                .setUserName("Albert")
                .setEmail("AlbertEinstein1879@mail.com")
                .build();
        UserServiceOuterClass.User userWithNewEmail = userStub.changeUserEmail(userToChangeEmail);
        System.out.println("User with changed email: \n " + userWithNewEmail);


        UserServiceOuterClass.User userToChangeName = UserServiceOuterClass.User
                .newBuilder()
                .setId(userResponse.getId())
                .setUserName("Albert Einstein")
                .setEmail("AlbertEinstein1879@mail.com")
                .build();
        UserServiceOuterClass.User userWithNewName = userStub.changeUserName(userToChangeName);
        System.out.println("User with changed email: \n " + userWithNewName);

        //product
        ProductServiceGrpc.ProductServiceBlockingStub productStub = ProductServiceGrpc.newBlockingStub(channel);

        ProductServiceOuterClass.Product product = ProductServiceOuterClass.Product
                .newBuilder()
                .setName("Book")
                .build();
        ProductServiceOuterClass.ProductResponse productResponse = productStub.createProduct(product);
        System.out.println("Created product: \n " + productResponse);

        ProductServiceOuterClass.Product productToCart = ProductServiceOuterClass.Product
                .newBuilder()
                .setId(productResponse.getId())
                .setName("Book")
                .build();
        ProductServiceOuterClass.Product product1 = productStub.addProductToCart(productToCart);
        System.out.println("Added to cart: \n " + product1);

        channel.shutdownNow();
    }
}
