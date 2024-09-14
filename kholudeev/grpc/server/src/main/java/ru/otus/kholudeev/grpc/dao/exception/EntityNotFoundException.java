package ru.otus.kholudeev.grpc.dao.exception;

public class EntityNotFoundException extends DaoException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
