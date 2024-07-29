package ru.otus.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.core.service.HashPasswordService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Anton Ponomarev on 29.07.2024
 * @project Java-Advanced-homework
 */
@RequiredArgsConstructor
@Slf4j
public class HashPasswordServiceImpl implements HashPasswordService {
  @Override
  public String hashPassword(String password, String algorithm) {
    String generatedPassword = null;
    try
    {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance(algorithm);

      // Add password bytes to digest
      md.update(password.getBytes());

      // Get the hash's bytes
      byte[] bytes = md.digest();

      // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      // Get complete hashed password in hex format
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }
}
