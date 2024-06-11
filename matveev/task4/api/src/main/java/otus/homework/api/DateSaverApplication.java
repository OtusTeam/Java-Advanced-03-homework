package otus.homework.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import otus.homework.provider.DataProvider;
import otus.homework.service.DateService;

@SpringBootApplication
public class DateSaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateSaverApplication.class, args);
    }
}
