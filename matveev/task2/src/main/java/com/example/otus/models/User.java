package com.example.otus.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer password;

    public User() {}

    public User(String name, String email, Integer password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
