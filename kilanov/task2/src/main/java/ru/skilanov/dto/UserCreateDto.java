package ru.skilanov.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDto {
    @NotNull(message = "Login can not be null")
    @Size(min = 2, message = "Login must not be less than two characters")
    private String login;
    @NotNull(message = "Password can not be null")
    @Size(min = 8, max = 16, message = "Password should be less than 16 characters")
    private String password;
}
