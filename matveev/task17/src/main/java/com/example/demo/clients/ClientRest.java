package com.example.demo.clients;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class ClientRest {

    public String callApi() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100));
    }
}
