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
		 PasswordHasher hasher = context.getBean(PasswordHasher.class);
		
		assertEquals("827ccb0eea8a706c4c34a16891f84e7b",hasher.hash("12345"));
		
		
	}
	
	@Test
	public void test256Hashes() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		PasswordHasher hasher = context.getBean("sha-256",PasswordHasher.class);
		assertEquals("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5",hasher.hash("12345"));
		
		
	}
	
	@Test
	public void test512Hashes() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		PasswordHasher hasher = context.getBean("sha-512",PasswordHasher.class);
		assertEquals("3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79",hasher.hash("12345"));
		
		
	}
}
