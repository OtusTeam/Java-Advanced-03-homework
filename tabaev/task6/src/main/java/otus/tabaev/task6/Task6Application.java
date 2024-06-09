package otus.tabaev.task6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class Task6Application implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(Task6Application.class, args);
	}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Runtime.getRuntime().halt(-777);
    }

}
