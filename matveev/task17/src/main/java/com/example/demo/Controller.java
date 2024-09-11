package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ClientApi clientApi;

    public Controller(ClientApi clientApi) {
        this.clientApi = clientApi;

    }

    @GetMapping("/getAge/{id}")
    public String getAge(@PathVariable("id") Integer id) {
        return clientApi.getAge(id);
    }
}
