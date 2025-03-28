package com.openclassrooms.mddapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Private method to create a security scheme based on JWT tokens.
     *
     * @return SecurityScheme : a security scheme for APIs using "Bearer" tokens.
     * Explanation:
     * - Swagger (OpenAPI) allows defining security mechanisms for APIs.
     * - Here, we configure an HTTP scheme that uses "Bearer tokens",
     *   typically JSON Web Tokens (JWT), to authenticate requests.
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    /**
     * Bean to configure Swagger/OpenAPI.
     *
     * @return OpenAPI : the object containing the complete API configuration.
     * Explanation:
     * - This method exposes an OpenAPI bean, which will be used by SpringDoc
     *   to generate the interactive Swagger documentation for the API.
     * - It configures API details like title, version, and contact information,
     *   as well as authentication mechanisms.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("MDD REST API")
                        .version("1.0")
                        .contact(new Contact().name("Test test").email( "pro@test.com")));
    }
}
