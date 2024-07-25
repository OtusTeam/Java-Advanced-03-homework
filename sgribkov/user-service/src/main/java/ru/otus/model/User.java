package ru.otus.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_data")
public class User implements Persistable<String> {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    @Nonnull
    private String password;

    @Transient
    private boolean isNew;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isNew = false;
    }

    @Override
    public String getId() {
        return this.login;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
