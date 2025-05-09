package com.openclassrooms.mddapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

// This class is a Data Transfer Object (DTO) used to represent the data required for user registration.
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
