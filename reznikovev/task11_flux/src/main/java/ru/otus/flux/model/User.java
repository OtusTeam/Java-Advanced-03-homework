package ru.otus.flux.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "user_app")
@Data
public class User {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;
}
