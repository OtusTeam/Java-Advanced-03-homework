package ru.otus.model;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String login;

    private String password;
}
