package ru.skilanov.exception;

public class NoSuchDirectoryException extends RuntimeException{
    public NoSuchDirectoryException(String message) {
        super(message);
    }
}
