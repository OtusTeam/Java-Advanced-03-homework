package otus.moryakovdv.task11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.r2dbc.spi.ConnectionFactory;
import otus.moryakovdv.task11.service.Md5Hasher;
import otus.moryakovdv.task11.service.PasswordHasher;
import otus.moryakovdv.task11.service.SHA256Hasher;
import otus.moryakovdv.task11.service.SHA512Hasher;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableWebFlux
@EnableAutoConfiguration
@EnableConfigurationProperties
/*** Запуск SpringBoot приложения */
public class WebApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	/*** Подстановка хешировщика в контекст */
	@Bean
	@Primary
	public PasswordHasher getDefaultPaswordHasherImplementation() {

		return new Md5Hasher();

	}

	@Bean(name = "sha-256")
	@Scope("prototype")
	public PasswordHasher get256HasherImplementation() {

		return new SHA256Hasher();

	}

	@Bean(name = "sha-512")
	@Scope("prototype")
	public PasswordHasher get512HasherImplementation() {

		return new SHA512Hasher();

	}

}
