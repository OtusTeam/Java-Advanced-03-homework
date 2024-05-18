
package otus.moryakovdv.task2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
public class WebApplication {

	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	
	
	

}
