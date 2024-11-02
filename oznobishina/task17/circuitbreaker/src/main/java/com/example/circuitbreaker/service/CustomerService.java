package com.example.circuitbreaker.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.circuitbreaker.client.ClientAdapter;

@Component
public class CustomerService {

    @Autowired
    private ClientAdapter clientAdapter;

    public Integer getAge(long customerId) {
        return clientAdapter.circuitBreakerApi();
    }
}
