package ru.otus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "user_data")
@Schema
public class User implements Persistable<String> {
    @Id
    @Column(value = "login")
    @Schema(description = "User login")
    private String login;

    @Column(value = "password")
    @Nonnull
    @Schema(description = "User password")
    private String password;

    @Transient
    @JsonIgnore
    private boolean isNew;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isNew = true;
    }

    @Override
    @JsonIgnore
    public String getId() {
        return this.login;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
