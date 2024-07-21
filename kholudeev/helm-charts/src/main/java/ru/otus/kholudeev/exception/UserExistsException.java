package ru.otus.kholudeev.exception;

public class UserExistsException extends ApplicationException {
    public UserExistsException(String message) {
        super(message);
    }
}
