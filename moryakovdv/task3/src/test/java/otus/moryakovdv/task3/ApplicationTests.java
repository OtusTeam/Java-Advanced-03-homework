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

package otus.moryakovdv.task3;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponentsBuilder;

import otus.moryakovdv.task3.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
@DirtiesContext
public class ApplicationTests {

	


	@LocalServerPort
	private int port;

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://127.0.0.1:" + this.port,
				String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue(entity.getBody().contains("USAGE"));
	}

	@Test
	public void testGetAllUsers() {
		
		URI uri= UriComponentsBuilder.fromHttpUrl(String.format("http://127.0.0.1:%d",this.port))
				 .path("/user/all")
				 .build().toUri();
				 
				 
				
				ResponseEntity<List> entity1 = new TestRestTemplate().getForEntity(
						uri, List.class);
				
				assertEquals(200, entity1.getStatusCode());
	}

	@Test
	public void testLogin() {
		 
		 
		URI uri= UriComponentsBuilder.fromHttpUrl(String.format("http://127.0.0.1:%d",this.port))
		 .path("/user/login")
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
