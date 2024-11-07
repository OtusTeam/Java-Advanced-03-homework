package ru.otus.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "User service", version = "1.0",
                description = "Service for user registration.",
                license = @License(name = "sgribkov")))
public class OpenApiConfig {
}
