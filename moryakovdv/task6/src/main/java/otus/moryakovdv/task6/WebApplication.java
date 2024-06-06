package otus.moryakovdv.task6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import otus.moryakovdv.task6.service.Md5Hasher;
import otus.moryakovdv.task6.service.PasswordHasher;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
/***Запуск SpringBoot приложения*/
public class WebApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(WebApplication.class, args);
	}


	/***Подстановка хешировщика в контекст*/
	@Bean
	public PasswordHasher getPaswordHasherImplementation() {
		
		return new Md5Hasher();
		
	}
}
