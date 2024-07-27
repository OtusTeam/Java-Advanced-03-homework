package ru.otus.kholudeev.dao.exception;

public class UserNotFoundException extends DaoException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
