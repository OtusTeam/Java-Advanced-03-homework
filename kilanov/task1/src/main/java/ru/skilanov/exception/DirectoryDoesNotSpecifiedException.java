package ru.skilanov.exception;

public class DirectoryDoesNotSpecifiedException extends RuntimeException{
    public DirectoryDoesNotSpecifiedException(String message) {
        super(message);
    }
}
