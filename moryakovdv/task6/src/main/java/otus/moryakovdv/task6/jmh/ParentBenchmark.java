package otus.moryakovdv.task6.jmh;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.openjdk.jmh.annotations.Benchmark;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task6.service.Md5Hasher;
import otus.moryakovdv.task6.service.SHA256Hasher;
import otus.moryakovdv.task6.service.SHA512Hasher;

@Slf4j
/**базовый класс для описания тестов. 
 * Содержит методы хеширования по различным алгоритмам.
 *  замеры производительности по различным метрикам реализуются в наследниках аннотациями JMH*/
public class ParentBenchmark {

	protected static final String INPUT = "The quick brown fox jumps over the lazy dog";

	protected static final Md5Hasher md5hasher = new Md5Hasher();
	protected static final SHA256Hasher sha256hasher = new SHA256Hasher();
	protected static final SHA512Hasher sha512hasher = new SHA512Hasher();

	public ParentBenchmark() {
		log.info("md5hasher created: {}", md5hasher);
		log.info("sha256hasher created: {}", sha256hasher);
		log.info("sha512hasher created: {}", sha512hasher);

	}

	protected String hashMd5() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		return md5hasher.hash(INPUT);
	}

	protected String hash256() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return sha256hasher.hash(INPUT);

	}

	protected String hash512() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return sha512hasher.hash(INPUT);

	}
	
	@Benchmark
	public void measureMd5() throws InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException {
		hashMd5();
	}
	@Benchmark
	public void measureSha256() throws InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException {
		hash256();
	}
	@Benchmark
	public void measureSha512() throws InterruptedException, UnsupportedEncodingException, NoSuchAlgorithmException {
		hash512();
	}



}
