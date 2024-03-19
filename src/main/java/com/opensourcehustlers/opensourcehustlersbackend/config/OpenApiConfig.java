package com.opensourcehustlers.opensourcehustlersbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            contact = @Contact(name = "Peter Ko", url = "https://www.github.com/imko"),
            description = "Open API documentation for Open Source Hustlers",
            title = "Open API Specification - Open Source Hustlers",
            version = "1.0"),
    servers = {
      @Server(description = "dev", url = "http://localhost:8080"),
      @Server(description = "prod", url = "https://www.github.com/imko"),
    })
@SecurityScheme(
    name = "bearerToken",
    description =
        "Bearer token is required to make requests to the server. Please authenticate with Postman at http://localhost:8080/v1/api/auth/login",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {}
