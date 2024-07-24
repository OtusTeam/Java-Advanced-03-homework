package com.homework.otus.utils;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

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

    @SneakyThrows
    public synchronized String encrypt(String encryptType, String password) {
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
                Thread.sleep(1000);
                throw new NoSuchMethodException("Encrypt type not found");
            }
        }
    }



}
