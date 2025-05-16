package com.openclassrooms.mddapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for user login.
 *
 * <p>This record represents the data required to authenticate a user,
 * including email and password fields used in login requests.</p>
 *
 * @param email the email address of the user
 * @param password the password of the user
 */
public record LoginUserDTO(

        @Schema(description = "User email")
        String email,

        @Schema(description = "User password")
        String password
){}