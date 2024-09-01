package ru.otus.kholudeev.grpc.mapper;

import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.Product;
import ru.otus.kholudeev.grpc.ProductRequest;
import ru.otus.kholudeev.grpc.dao.model.DaoProduct;

@Service
public class ProductMapper {
    public DaoProduct toProduct(ProductRequest request) {
        return DaoProduct.builder()
                .name(request.getName())
                .build();
    }

    public Product toResponse(DaoProduct product) {
        return Product.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .build();
    }
}
