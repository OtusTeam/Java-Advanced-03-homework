package ru.otus.service.encryptor;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.otus.model.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Getter
@Setter
@Component
public class UserEncryptorImpl implements UserEncryptor {

    private final String charset;
    private final String algorithm;
    private final boolean isSalted;

    public UserEncryptorImpl(@Value("${passwordEncryptor.charset}") String charset,
                             @Value("${passwordEncryptor.algorithm}") String algorithm,
                             @Value("${passwordEncryptor.isSalted}") boolean isSalted) {
        this.charset = charset;
        this.algorithm = algorithm;
        this.isSalted = isSalted;
    }

    public UserEncryptorImpl() {
        this.charset = "UTF-8";
        this.algorithm = "SHA-256";
        this.isSalted = true;
    }

    @Override
    public User encrypt(User user) {
        try {
            String pwd = user.getPassword();
            byte[] pwdData = pwd.getBytes(charset);

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            if (isSalted) {
                messageDigest.update(salt().getBytes());
            }

            byte[] pwdDataDigested = messageDigest.digest(pwdData);
            String pwdEncrypted = Base64.getEncoder().encodeToString(pwdDataDigested);
            user.setPassword(pwdEncrypted);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Can not encrypt password.", e);
        }
        return user;
    }

    private static String salt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
