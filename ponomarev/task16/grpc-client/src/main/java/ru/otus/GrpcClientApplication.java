package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.grpc.UserID;

/**
 * @author Anton Ponomarev on 05.09.2024
 * @project Default (Template) Project
 */
@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);

        var clientService = applicationContext.getBean(ClientService.class);
        UserID johnDoeId = clientService.createUser("John Doe", "johnDoe@gmail.com");
        clientService.changeUserEmail(johnDoeId.getValue(), "newEmail@mail.ru");
        clientService.changeUserName(johnDoeId.getValue(), "newName");
        clientService.createProduct("newPeoduct");
        clientService.addProductToCart("1", "11");
    }
}