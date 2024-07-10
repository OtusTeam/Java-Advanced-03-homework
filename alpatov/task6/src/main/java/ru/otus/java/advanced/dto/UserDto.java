package ru.otus.java.advanced.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String login;
    private String password;
}
