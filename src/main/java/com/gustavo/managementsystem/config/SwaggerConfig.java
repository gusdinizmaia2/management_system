package com.gustavo.managementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customSwaggerAPI() {

        var securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer").bearerFormat("JWT");

        var components = new Components()
            .addSecuritySchemes("bearer-key", securityScheme);

        var info = new Info()
            .title("Management System API")
            .version("1.0")
            .description("Documentation API Management System");


        return new OpenAPI()
            .components(components)
            .info(info);
    }
}
