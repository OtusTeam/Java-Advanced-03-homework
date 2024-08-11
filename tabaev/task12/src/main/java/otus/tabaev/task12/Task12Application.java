package otus.tabaev.task12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class Task12Application implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(Task12Application.class, args);
	}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Runtime.getRuntime().halt(-777);
    }

}
