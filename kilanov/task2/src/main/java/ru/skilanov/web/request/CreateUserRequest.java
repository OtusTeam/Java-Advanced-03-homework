package ru.skilanov.web.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotNull(message = "Login can not be null")
    @Size(min = 2, message = "Login must not be less than two characters")
    private String login;
    @NotNull(message = "Password can not be null")
    @Size(min = 8, max = 16, message = "Password should be less than 16 characters")
    private String password;
}
