package ru.otus.kholudeev.grpc.dao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.dao.exception.DaoException;
import ru.otus.kholudeev.grpc.dao.exception.EntityNotFoundException;
import ru.otus.kholudeev.grpc.dao.model.DaoProduct;
import ru.otus.kholudeev.grpc.dao.repository.ProductRepository;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRepoService {
    private final ProductRepository repo;
    public Optional<DaoProduct> findById(Long id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            log.error("Ошибка получения продукта по ID {}", id, e);
            throw new DaoException("Ошибка получения продукта по ID", e);
        }
    }

    public DaoProduct getById(Long id) {
        return findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Не найден продукт, id - %s", id)));
    }

    public DaoProduct save(DaoProduct daoProduct) {
        try {
            return repo.save(daoProduct);
        } catch (Exception e) {
            log.error("Ошибка сохранения продукта, наименование - {}", daoProduct.getName(), e);
            throw new DaoException(format("Ошибка сохранения продукта, наименование - %s", daoProduct.getName()), e);
        }
    }
}
