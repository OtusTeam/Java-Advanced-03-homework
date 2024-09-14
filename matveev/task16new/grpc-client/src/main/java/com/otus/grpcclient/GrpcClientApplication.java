package com.otus.grpcclient;

import com.otus.grpcclient.Service.ShopClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);
		ShopClient shopServer = applicationContext.getBean(ShopClient.class);
		shopServer.createUser("1", "Joe", "joe@friends.com");
		shopServer.changeUserEmail("1","MathieuBlanc@friends.com");
		shopServer.changeUserName("1","Mathieu Blanc");
		shopServer.createProduct("99","Jokes");
		shopServer.addProductToCard("1","99");
	}
}