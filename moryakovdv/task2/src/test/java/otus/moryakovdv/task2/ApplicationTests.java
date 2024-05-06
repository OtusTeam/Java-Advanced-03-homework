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

package otus.moryakovdv.task2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponentsBuilder;

import otus.moryakovdv.task2.WebApplication;
import otus.moryakovdv.task2.model.User;
import otus.moryakovdv.task2.service.LoginFailedException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
@DirtiesContext
public class ApplicationTests {

	@BeforeAll
	public static void beforeAll() throws Exception {

		WebApplication.main(new String[] {});
	}
	


	@Value("${local.server.port:8081}")
	private int port;

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://127.0.0.1:" + this.port,
				String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue(entity.getBody().contains("USAGE"));
	}

	

	@Test
	public void testLogin() {
		 
		// testFailLogin();		 
		 
		URI uri= UriComponentsBuilder.fromHttpUrl(String.format("http://127.0.0.1:%d",this.port))
		 .path("/login")
		 .queryParam("userName", "user2")
		 .queryParam("passwd", "user2")
		 .queryParam("createIfAbsent", "true")
		 .build().toUri();
		 
		 
		
		ResponseEntity<User> entity1 = new TestRestTemplate().getForEntity(
				uri, User.class);
		
		assertInstanceOf(User.class, entity1.getBody());
	}
	
	@Test
	public void testProcess() {
		 
		UUID sid = UUID.randomUUID();		 
		 
		URI uri= UriComponentsBuilder.fromHttpUrl(String.format("http://127.0.0.1:%d",this.port))
		 .path("/process")
		 .queryParam("sessionId", sid.toString())
		 .build().toUri();
		 
		 
		
		ResponseEntity<String> entity1 = new TestRestTemplate().getForEntity(
				uri, String.class);
		
		assertTrue(entity1.getBody().toString().contains("Processed queries"));
	}

}
