package com.example.otus.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component("Utils")
public class Utils {

    public String md5Encrypt(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();

    }

    public String sha256Encrypt(String password) {
        return DigestUtils.sha256Hex(password).toUpperCase();

    }

    public String sha512Encrypt(String password) {
        return DigestUtils.sha512Hex(password);

    }

    public String encrypt(String encryptType, String password) {
        switch (encryptType) {
            case "md5" -> {
                return md5Encrypt(password);
            }
            case "sha256" -> {
                return sha256Encrypt(password);
            }
            case "sha512" -> {
                return sha512Encrypt(password);
            }
            default -> {
                return "Encrypt type not found: " + encryptType;
            }
        }
    }
}
