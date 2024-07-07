package com.ozn.reactor.endpoint;

import com.ozn.reactor.model.Customer;

public class CustomerDto {
    private Long id;
    private String login;
    private String email;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.login = customer.getLogin();
        this.email = customer.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
