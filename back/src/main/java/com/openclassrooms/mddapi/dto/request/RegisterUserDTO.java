package com.openclassrooms.mddapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

/**
 * DTO for user registration.
 *
 * <p>This record is used to transfer user information required for account creation,
 * including email, name, and a password that meets security constraints.</p>
 *
 * @param email the email address of the user
 * @param name the name of the user
 * @param password the password that meets complexity requirements
 */
public record RegisterUserDTO(

        @Schema(description = "User email")
        String email,

        @Schema(description = "User name")
        String name,

        @Schema(description = "User password")
        @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "The password must contain at least 8 characters, including one lowercase letter, one uppercase letter, one digit, and one special character."
        )
        String password
){}
