package com.example.provider;


import com.example.core.DataStore;
import com.example.model.Customer;

public class CustomerProvider {

    private DataStore dataCore = new DataStore();

    public Customer getCustomerById(long i) {
       return dataCore.getCustomerById(i);
    }
}
