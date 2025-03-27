package com.openclassrooms.mddapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to represent the data required for user login.
public record LoginUserDTO(

        @Schema(description = "User email")
        String email,

        @Schema(description = "User password")
        String password
){}