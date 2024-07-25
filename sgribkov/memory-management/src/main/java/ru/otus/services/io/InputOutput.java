package ru.otus.services.io;

public interface InputOutput {
    void out(String message);
    String readLn(String prompt);
    void close(String prompt);
}
