package com.example.circuitbreaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.circuitbreaker.service.CustomerService;
import jdk.jfr.ContentType;

@RestController
@RequestMapping("/customer")
public class Controller {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/age/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getAge(@PathVariable("id") long customerId) {
        return customerService.getAge(customerId);
    }
}
