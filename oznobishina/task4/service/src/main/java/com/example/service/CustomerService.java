package com.example.service;

import com.example.model.Customer;
import com.example.provider.CustomerProvider;

public class CustomerService {

    private CustomerProvider customerProvider = new CustomerProvider();

    public Customer getCustomerById(long i) {
        return customerProvider.getCustomerById(i);
    }
}
