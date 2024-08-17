package com.example.otus.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "My Homework 15", version = "1.0",
                license = @License(name = "Pupkin")),
        servers = {
                @Server(description = "Application available on localhost")
        } )
public class OpenAPIConfig {}
