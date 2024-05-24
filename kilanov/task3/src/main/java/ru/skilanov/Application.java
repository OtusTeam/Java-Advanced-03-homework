package ru.skilanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class Application implements ApplicationListener<ContextRefreshedEvent> {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Runtime.getRuntime().halt(-777);
	}
}
