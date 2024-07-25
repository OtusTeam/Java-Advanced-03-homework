package com.ozn.reactor.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ozn.reactor.model.Customer;
import com.ozn.reactor.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> getAll() {
        return customerService.findAll().map(CustomerDto::new);
    }

    @GetMapping("/emails")
    public Flux<String> getAllEmails() {
        return customerService.findAllEmails();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CustomerDto> login(@RequestBody Customer customer) {
        return customerService.findCustomer(customer.getLogin(), customer.getPassword()).map(CustomerDto::new);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
