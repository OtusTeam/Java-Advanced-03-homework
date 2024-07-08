package com.ozn.reactor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ozn.reactor.model.Customer;
import com.ozn.reactor.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CustomerService {

    public static final int ITERATIOIN_NUMBER = 1;

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<Customer> findCustomer(String login, String password) {
        String hashedPassword = HashUtils.hash512(password, ITERATIOIN_NUMBER);
        return customerRepository.findOne(login, hashedPassword);
    }

    public Mono<Customer> saveCustomer(Customer customer) {
        String hashedPassword = HashUtils.hash512(customer.getPassword(), ITERATIOIN_NUMBER);
        customer.setPassword(hashedPassword);

        return customerRepository.save(customer);
    }

    public Flux<Customer> findAll() {
       return customerRepository.findAll();
    }

    public Flux<String> findAllEmails() {
        return customerRepository.findAllEmails();
    }

}
