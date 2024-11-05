package com.example.circuitbreaker.client;

import org.springframework.stereotype.Component;

@Component
public class ClientRest {
    public Integer callApi() {
        return 18;
    }
}
