package com.example.core;

import java.util.HashMap;
import java.util.Map;

import com.example.model.Customer;

public class DataStore {

    private final Map<Long, Customer> customers = new HashMap();

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
