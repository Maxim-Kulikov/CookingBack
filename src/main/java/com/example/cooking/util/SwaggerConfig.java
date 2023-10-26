package com.example.cooking.util;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1",
        contact = @Contact(name = "Maxim Kulikov", email = "kulikov.m.a102@gmail.com")),
        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")}
)
*/
@SecuritySchemes({
        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
        //@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
public class SwaggerConfig {
}
