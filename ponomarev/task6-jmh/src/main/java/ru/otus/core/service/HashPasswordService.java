package ru.otus.core.service;

/**
 * @author Anton Ponomarev on 29.07.2024
 * @project Java-Advanced-homework
 */
public interface HashPasswordService {
    String hashPassword(String password, String algorithm);
}
