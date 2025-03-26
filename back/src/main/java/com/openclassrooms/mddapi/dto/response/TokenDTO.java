package com.openclassrooms.mddapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to represent a token in a response.
public record TokenDTO(
    @Schema(description = "Token used to authenticate user")
    String token
) {
}