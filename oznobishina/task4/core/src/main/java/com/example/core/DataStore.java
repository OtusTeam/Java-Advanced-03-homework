package com.example.core;

import com.example.model.Customer;

public class DataStore {
    public Customer getCustomerById(long i) {
        return Customer.builder()
                .id(i)
                .name("John")
                .email("john@email.com")
                .build();
    }
}
