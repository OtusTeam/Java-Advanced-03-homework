package ru.skilanov.exception;

public class FileDoesntExistException extends RuntimeException{

    public FileDoesntExistException(String message) {
        super(message);
    }
}
