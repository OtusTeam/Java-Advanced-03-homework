package ru.skilanov.web.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreateResponse implements Serializable {
    private String login;
    private String password;
}
