package ru.otus.kholudeev.grpc.dao.exception;

public class UserNotFoundException extends DaoException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
