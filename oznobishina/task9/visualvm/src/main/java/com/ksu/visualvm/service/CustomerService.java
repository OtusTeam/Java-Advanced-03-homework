package com.ksu.visualvm.service;

import com.ksu.visualvm.model.Customer;
import com.ksu.visualvm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    public static final int ITERATIOIN_NUMBER = 1;
    public static final int NUMBER_OF_DUPLICATIONS = 100;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomer(String login, String password) {
        String hashedPassword = org.ksu.service.HashService.hash512(password, ITERATIOIN_NUMBER);
        validate(login);

        return customerRepository.findOne(login, hashedPassword);
    }

    private void validate(String login) {
        for (int i = 0; i < NUMBER_OF_DUPLICATIONS; i++) {
            customerRepository.find(login);
        }
    }

    public Customer saveCustomer(Customer customer) {
        String hashedPassword = org.ksu.service.HashService.hash512(customer.getPassword(), ITERATIOIN_NUMBER);
        customer.setPassword(hashedPassword);

        return customerRepository.save(customer);
    }
}
