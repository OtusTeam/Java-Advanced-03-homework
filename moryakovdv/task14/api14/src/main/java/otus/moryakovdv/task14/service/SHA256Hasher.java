package otus.moryakovdv.task14.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**реализация SHA-256 хеширования*/
public class SHA256Hasher implements PasswordHasher {

	@Override
	public synchronized String hash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
		byte[] array = md.digest(input.getBytes("UTF-8"));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		String result = sb.toString();
		log.debug("Hash calculated: {}",result);
		return result;
	}

}
