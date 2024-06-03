package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentity {
    private String login;
    private String id;
    private int hashCode;

    public UserIdentity(User user) {
        this.login = user.getId();
        this.id = UUID.nameUUIDFromBytes(login.getBytes())
                .toString();
        this.hashCode = user.hashCode();
    }

    @Override
    public boolean equals(Object  o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
