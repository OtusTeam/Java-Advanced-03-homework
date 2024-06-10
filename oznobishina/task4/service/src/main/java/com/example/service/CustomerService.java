package com.example.service;

import com.example.core.DataStore;
import com.example.model.Customer;
import com.example.provider.CustomerProvider;

public class CustomerService {

    private CustomerProvider customerProvider = new CustomerProvider();
    private DataStore dataStore = new DataStore();

    public Customer getCustomerById(long i) {
        return customerProvider.getCustomerById(i);
    }

    public void save(Customer customer) {
        dataStore.save(customer);
    }
}
