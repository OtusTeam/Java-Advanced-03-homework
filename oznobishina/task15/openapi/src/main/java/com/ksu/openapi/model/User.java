package com.ksu.openapi.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
