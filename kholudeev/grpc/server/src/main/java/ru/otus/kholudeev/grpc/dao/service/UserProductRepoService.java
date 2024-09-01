package ru.otus.kholudeev.grpc.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kholudeev.grpc.Product;
import ru.otus.kholudeev.grpc.dao.model.DaoProduct;
import ru.otus.kholudeev.grpc.dao.model.DaoUser;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserProductRepoService {
    private final UserRepoService userRepoService;
    private final ProductRepoService productRepoService;

    @Transactional
    public void linkProductToUser(long userId, long productId) {
        DaoUser user = userRepoService.getById(userId);
        Set<DaoProduct> products = user.getProducts();
        products.add(productRepoService.getById(productId));
        user.setProducts(products);
        userRepoService.save(user);
    }
}
