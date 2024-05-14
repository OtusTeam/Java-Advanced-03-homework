package com.ex.memorydump.service;

import com.ex.memorydump.model.Customer;
import com.ex.memorydump.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerService {

    private Map<Long, List<Long>> cache = new ConcurrentHashMap<>();

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomer(String login, String password) {
           var customer = customerRepository.findOne(login, password);

           if (customer != null) {
               loadSmthHeavyInMemory();
           }

           return customer;
    }

    private void loadSmthHeavyInMemory() {
        List<Long> list = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            list.add((long) i);
        }
        cache.put((long) cache.size(), list);
    }
}
