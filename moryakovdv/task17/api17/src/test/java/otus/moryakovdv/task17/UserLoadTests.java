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

package otus.moryakovdv.task17;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = { otus.moryakovdv.task17.repository.UsersCrudRepository.class,
		otus.moryakovdv.task17.web.UserController.class })
@AutoConfigureMockMvc
@ComponentScan
class UserLoadTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testRPM() throws Exception {

		this.mockMvc.perform(get("/getUserAgeRPM?id=15"))
				// .andDo(print())
				.andExpect(status().isOk());
	}

	// @Test
	public void testRPS() throws Exception {
		this.mockMvc.perform(get("/getUserAgeRPS?id=16"))
				// .andDo(print())
				.andExpect(status().isOk());

	}

	// @Test
	public void testCB() throws Exception {
		this.mockMvc.perform(get("/getUserAgeCB?id=17")).andDo(print()).andExpect(status().isOk());
	}
}
