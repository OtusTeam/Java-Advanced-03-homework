package ru.otus.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "User service", version = "1.0",
                description = "Service for user registration.",
                license = @License(name = "sgribkov")))/*,
        servers = {
                @Server(url = "http://localhost:8080/user-service")
        })*/
public class OpenApiConfig {
}
