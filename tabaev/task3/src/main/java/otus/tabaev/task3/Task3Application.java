package otus.tabaev.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class Task3Application implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Runtime.getRuntime().halt(-777);
    }

}
