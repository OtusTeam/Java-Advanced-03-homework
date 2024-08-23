package org.ksu.service;

import org.ksu.model.Customer;
import org.ksu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    public static final int ITERATIOIN_NUMBER = 1;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomer(String login, String password) {
        String hashedPassword = HashService.hash512(password, ITERATIOIN_NUMBER);
        validate(login);

        return customerRepository.findOne(login, hashedPassword);
    }

    private void validate(String login) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();

    }

    public Customer saveCustomer(Customer customer) {
        String hashedPassword = HashService.hash512(customer.getPassword(), ITERATIOIN_NUMBER);
        customer.setPassword(hashedPassword);

        return customerRepository.save(customer);
    }
}
