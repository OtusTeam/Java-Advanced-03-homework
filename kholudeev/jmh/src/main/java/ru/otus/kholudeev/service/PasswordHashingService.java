package ru.otus.kholudeev.service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;

@Service
@NoArgsConstructor
public class PasswordHashingService {

    @SneakyThrows
    public String getHashedPassword(String password, String algorithm) {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] salt = BCrypt.gensalt().getBytes();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hashedBytes);
    }

    public Boolean matchPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
