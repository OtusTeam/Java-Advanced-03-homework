package com.example.demo.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Random;

@Slf4j
@Component
public class ClientRest {

    public String callApi(Integer id) {
        Random random = new Random();
        return String.valueOf(random.nextInt(80));
    }
}
