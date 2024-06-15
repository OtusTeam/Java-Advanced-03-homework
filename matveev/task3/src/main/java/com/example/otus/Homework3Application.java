package com.example.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class Homework3Application {

	public static void main(String[] args) {
		StopWatch stopWatch = new StopWatch("App");
		stopWatch.start("App Startup");
		SpringApplication.run(Homework3Application.class, args);
		stopWatch.stop();
		System.out.println("Seconds = "+stopWatch.getTotalTimeSeconds());
		System.out.println(stopWatch.prettyPrint());
	}

}
