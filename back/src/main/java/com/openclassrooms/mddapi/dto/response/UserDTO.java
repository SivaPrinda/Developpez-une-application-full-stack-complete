package com.openclassrooms.mddapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

// This class is a Data Transfer Object (DTO) used to represent a user properties in a response.
public record UserDTO(

        @Schema(description = "User id")
        Long id,

        @Schema(description = "User name")
        String name,

        @Schema(description = "User email")
        String email,

        @Schema(description = "User creation date")
        @JsonProperty("created_at")
        Instant createdAt,

        @Schema(description = "User update date")
        @JsonProperty("updated_at")
        Instant updatedAt
){}
