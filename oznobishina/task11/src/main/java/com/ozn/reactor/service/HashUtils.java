package com.ozn.reactor.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private static final String MD5 = "MD5";
    private static final String SHA256 = "SHA-256";
    private static final String SHA512 = "SHA-512";

    private static final byte[] SALT = "8SH+LTX=4PRJ".getBytes();

    public static String hashMD5(String password) {
        return hashPassword(password, MD5).toString();
    }

    public static String hash256(String password) {
        return hashPassword(password, SHA256).toString();
    }

    public static String hash512(String password, int n) {
        String hashedPassword = password;
        for (int i = 0; i < n; i++) {
            hashedPassword = hashPassword(hashedPassword, SHA512).toString();
        }
        return hashedPassword;
    }

    private static byte[] hashPassword(String passwordToHash, String algorithm) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
            md.update(SALT);
            return md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
