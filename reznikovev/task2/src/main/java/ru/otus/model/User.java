package ru.otus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_app")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;
}
