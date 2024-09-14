package ru.otus.kholudeev.grpc.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.*;
import ru.otus.kholudeev.grpc.dao.model.DaoUser;
import ru.otus.kholudeev.grpc.dao.service.UserProductRepoService;
import ru.otus.kholudeev.grpc.dao.service.UserRepoService;
import ru.otus.kholudeev.grpc.mapper.UserMapper;

@GrpcService
@Slf4j
@Service
@AllArgsConstructor
public class UserController extends UserInfoGrpc.UserInfoImplBase {
    private final UserRepoService repo;
    private final UserMapper mapper;
    private final UserProductRepoService userProductRepoService;

    @Override
    public void createUser(UserCreateRequest request, StreamObserver<User> responseObserver) {
        log.info("Начало создания пользователя, name - {}", request.getUsername());
        DaoUser user = mapper.toUser(request);
        repo.save(user);
        log.info("Пользователь успешно создан, id - {}, name - {}", user.getId(), user.getName());

        responseObserver.onNext(mapper.toResponse(user));
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserEmail(UserPutEmailRequest request, StreamObserver<User> responseObserver) {
        log.info("Начало изменения пользователя, id - {}", request.getId());
        DaoUser user = repo.getById(request.getId());
        user.setEmail(request.getEmail());
        repo.save(user);
        log.info("Пользователь успешно изменен, id - {}, email - {}", user.getId(), user.getEmail());

        responseObserver.onNext(mapper.toResponse(user));
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserName(UserPutNameRequest request, StreamObserver<User> responseObserver) {
        log.info("Начало изменения пользователя, id - {}", request.getId());
        DaoUser user = repo.getById(request.getId());
        user.setName(request.getUsername());
        repo.save(user);
        log.info("Пользователь успешно изменен, id - {}, name - {}", user.getId(), user.getName());

        responseObserver.onNext(mapper.toResponse(user));
        responseObserver.onCompleted();
    }

    @Override
    public void addProductToUser(UserProduct request, StreamObserver<UserProduct> responseObserver) {
        log.info("Начало добавление продукта в корзину пользователя, id пользователя - {}, id продукта - {}",
                request.getUserId(), request.getProductId());
        userProductRepoService.linkProductToUser(request.getUserId(), request.getProductId());
        log.info("Продукт добавлен в корзину пользователя, id пользователя - {}, id продукта - {}",
                request.getUserId(), request.getProductId());

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}