package org.memorydump.endpoint;

import org.memorydump.model.Customer;
import org.memorydump.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/login", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Customer login(@RequestBody Customer customer) {
        customerService.findCustomer(customer.getLogin(), customer.getPassword());
        return customer;
    }
}
