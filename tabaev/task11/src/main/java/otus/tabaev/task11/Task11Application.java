package otus.tabaev.task11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class Task11Application implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(Task11Application.class, args);
	}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Runtime.getRuntime().halt(-777);
    }

}
