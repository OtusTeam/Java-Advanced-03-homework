package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Anton Ponomarev on 05.09.2024
 * @project Default (Template) Project
 */
@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);

        var clientService = applicationContext.getBean(ClientService.class);
        clientService.createUser("John Doe", "johnDoe@gmail.com");
        clientService.changeUserEmail("1", "newEmail@mail.ru");
        clientService.changeUserName("1", "newName");
        clientService.createProduct("newPeoduct");
        clientService.addProductToCart("1", "1");
    }
}