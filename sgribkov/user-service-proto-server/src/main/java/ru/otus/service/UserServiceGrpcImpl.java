package ru.otus.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.*;
import ru.otus.dao.UserServiceDao;

@GrpcService
@RequiredArgsConstructor
public class UserServiceGrpcImpl extends ru.otus.UserServiceGrpc.UserServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(UserServiceGrpcImpl.class);

    private final UserServiceDao userServiceDao;

    @Override
    public void createUser(UserDto request, StreamObserver<UserId> responseObserver) {
        UserId userId = userServiceDao.saveUser(request);

        log.info("Saved user: {} with name: {} with email: {}",
                userId.getValue(),
                request.getUsername(),
                request.getEmail());

        responseObserver.onNext(userId);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserEmail(UserEmailDto request, StreamObserver<User> responseObserver) {
        User user = userServiceDao.updateUser(request);

        log.info("Updated user: {} with name: {} with new email: {}",
                user.getId(),
                user.getUsername(),
                user.getEmail());

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserName(UserNameDto request, StreamObserver<User> responseObserver) {
        User user = userServiceDao.updateUser(request);

        log.info("Updated user: {} with new name: {} with email: {}",
                user.getId(),
                user.getUsername(),
                user.getEmail());

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void createProduct(ProductDto request, StreamObserver<ProductId> responseObserver) {
        ProductId productId = userServiceDao.saveProduct(request);

        log.info("Saved product: {} with name: {}",
                productId.getValue(),
                request.getName());

        responseObserver.onNext(productId);
        responseObserver.onCompleted();
    }

    @Override
    public void addProductToCard(CardItemDto request, StreamObserver<CardId> responseObserver) {
        CardId cardId = userServiceDao.addProductToCard(request);

        log.info("Added product: {} for user: {} to card: {}",
                request.getProductId(),
                request.getUserId(),
                cardId.getValue());

        responseObserver.onNext(cardId);
        responseObserver.onCompleted();
    }
}
