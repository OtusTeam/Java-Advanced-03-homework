package otus.moryakovdv.task11;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task11.model.UserOtus;
import otus.moryakovdv.task11.repository.FluxUserRepository;
import reactor.core.publisher.Flux;

/**Класс тестирования рест-контроллера*/
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Slf4j
class ApplicationMockingTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	FluxUserRepository repo;

	@Autowired
	private DatabaseClient databaseClient;

	/**Пересоздание таблицы БД при каждом тесте**/
	@BeforeEach
	public void setup() {
		List<String> statements = Arrays.asList("DROP TABLE IF EXISTS USER_OTUS ;",
				"CREATE TABLE IF NOT EXISTS USER_OTUS (ID INT NOT NULL AUTO_INCREMENT, USER_NAME VARCHAR(255), PASSWORD VARCHAR(255), SESSION_ID VARCHAR(255), PRIMARY KEY (id));");

		statements.forEach(sql -> 
		databaseClient
		.sql(sql)
		.fetch()
		.rowsUpdated()
		.block());
	}

	@Test
	public void testRepository() {
		log.debug("Start tests");
		Flux<UserOtus> users = repo.findByUserNameAndPassword("5", "5");
		assertNotNull(users);

	}

	/**Тестируем рест-метод логина*/
	@Test
	public void testLogin() {
		log.debug("Login tests");

		webTestClient.get().uri("/login?passwd=5&userName=5&createIfAbsent=true")
		.exchange()
		.expectStatus()
		.isOk()
		.returnResult(UserOtus.class)
		.getResponseBody().subscribe(
				s->{
					
					assertEquals("5",s.getUserName());
					assertNotNull(s.getSessionId());
					System.out.println(s.getSessionId());
					
				}
				
				);
		
		
		
	}

}
