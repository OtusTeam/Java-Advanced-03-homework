package otus.moryakovdv.task6.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**реализация SHA-256 хеширования без StringBuffer*/
public class SHA256Hasher implements PasswordHasher {

	@Override
	public String hash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] data = input.getBytes("UTF-8");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] digest = messageDigest.digest(data);
		String result = new String(digest,"UTF-8");
		log.debug("Hash calculated: {}",result);
		return result;
	}

}
