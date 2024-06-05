/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package otus.moryakovdv.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import otus.moryakovdv.task6.service.Md5Hasher;
import otus.moryakovdv.task6.service.PasswordHasher;
import otus.moryakovdv.task6.service.SHA256Hasher;
import otus.moryakovdv.task6.service.SHA512Hasher;

@SpringBootTest()
class HasherTest {

	
	@Autowired
	private ApplicationContext context;
	
	
	
	
	@Test
	public void testMd5Hashes() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		 PasswordHasher hasher = context.getBean(Md5Hasher.class);
		
		assertEquals("827ccb0eea8a706c4c34a16891f84e7b",hasher.hash("12345"));
		
		
	}
	
	@Test
	public void test256Hashes() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		 PasswordHasher hasher = context.getBean(SHA256Hasher.class);

		assertEquals("827ccb0eea8a706c4c34a16891f84e7b",hasher.hash("12345"));
		
		
	}
	
	@Test
	public void test512Hashes() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		PasswordHasher hasher = context.getBean(SHA512Hasher.class);

		assertEquals("827ccb0eea8a706c4c34a16891f84e7b",hasher.hash("12345"));
		
		
	}
}
