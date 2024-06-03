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

    public UserIdentity(User user) {
        this.login = user.getId();
        this.id = UUID.nameUUIDFromBytes(login.getBytes())
                .toString();
    }
}
