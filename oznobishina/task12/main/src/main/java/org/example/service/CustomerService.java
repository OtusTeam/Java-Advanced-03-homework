package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    public static final int ITERATIOIN_NUMBER = 1;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomer(String login, String password) {
        String hashedPassword = HashService.hash512(password, ITERATIOIN_NUMBER);
        return customerRepository.findOne(login, hashedPassword);
    }

    public Customer saveCustomer(Customer customer) {
        String hashedPassword = HashService.hash512(customer.getPassword(), ITERATIOIN_NUMBER);
        customer.setPassword(hashedPassword);

        return customerRepository.save(customer);
    }
}
