package com.example.cooking.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "bearerToken", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "bearerToken")
public class SwaggerConfig {
}
