package ru.otus.kholudeev.grpc.dao.exception;

public class ProductNotFoundException extends DaoException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
