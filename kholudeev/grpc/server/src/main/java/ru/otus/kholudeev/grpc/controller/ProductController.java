package ru.otus.kholudeev.grpc.controller;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.*;
import ru.otus.kholudeev.grpc.dao.model.DaoProduct;
import ru.otus.kholudeev.grpc.dao.service.ProductRepoService;
import ru.otus.kholudeev.grpc.mapper.ProductMapper;

@GrpcService
@Slf4j
@Service
@AllArgsConstructor
public class ProductController extends ProductInfoGrpc.ProductInfoImplBase {
    private final ProductRepoService repo;
    private final ProductMapper mapper;

    @Override
    public void createProduct(ProductRequest request, StreamObserver<Product> responseObserver) {
        log.info("Начало создания продукта, name - {}", request.getName());
        DaoProduct product = mapper.toProduct(request);
        repo.save(product);
        log.info("Продукт успешно создан, id - {}, name - {}", product.getId(), product.getName());

        responseObserver.onNext(mapper.toResponse(product));
        responseObserver.onCompleted();
    }
}
