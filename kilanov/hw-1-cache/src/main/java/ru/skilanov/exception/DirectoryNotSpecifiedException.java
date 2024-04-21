package ru.skilanov.exception;

public class DirectoryNotSpecifiedException extends RuntimeException{
    public DirectoryNotSpecifiedException(String message) {
        super(message);
    }
}
