package otus.moryakovdv.task15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import otus.moryakovdv.task15.service.Md5Hasher;
import otus.moryakovdv.task15.service.PasswordHasher;
import otus.moryakovdv.task15.service.SHA256Hasher;
import otus.moryakovdv.task15.service.SHA512Hasher;

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
	@Primary
	public PasswordHasher getDefaultPaswordHasherImplementation() {
		
		return new Md5Hasher();
		
	}
	
	@Bean(name="sha-256")
	@Scope("prototype")
	public PasswordHasher get256HasherImplementation() {
		
		return new SHA256Hasher();
		
	}
	
	@Bean(name="sha-512")
	@Scope("prototype")
	public PasswordHasher get512HasherImplementation() {
		
		return new SHA512Hasher();
		
	}
	
	
	
	
	
}
