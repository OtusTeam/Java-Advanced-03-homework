package com.example.provider;


import com.example.model.Customer;

public class CustomerProvider {

    public Customer getCustomerById(long i) {
        return Customer.builder()
                .id(i)
                .name("John")
                .email("john@email.com")
                .build();
    }
}