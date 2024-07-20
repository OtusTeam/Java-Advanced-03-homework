package ru.otus.kholudeev.exception;

public class CacheKeyDoesntExistsException extends ApplicationException {
    public CacheKeyDoesntExistsException(String message) {
        super(message);
    }
}
