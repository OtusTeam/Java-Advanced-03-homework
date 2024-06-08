package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.model.Customer;
import com.example.service.CustomerService;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
       // SpringApplication.run(ApiApplication.class, args);

        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerById(1);

        System.out.println("Customer: " + customer.getName() + " " + customer.getEmail());
    }

}
