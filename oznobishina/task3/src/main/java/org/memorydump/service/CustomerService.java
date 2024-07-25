package org.memorydump.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.memorydump.model.Customer;
import org.memorydump.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private Map<Long, Long> cache = new ConcurrentHashMap<>();

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
        Map<Long, Long> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 100000; i++) {
            map.put((long) i, (long) i);
        }
        cache = map;
    }
}
