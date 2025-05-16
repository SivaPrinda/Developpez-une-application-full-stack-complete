package com.openclassrooms.mddapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * DTO for returning user data to the client.
 *
 * <p>This record encapsulates a user's ID, name, email, and timestamps
 * for account creation and last update, as returned in API responses.</p>
 *
 * @param id the unique identifier of the user
 * @param name the name of the user
 * @param email the user's email address
 * @param createdAt the date and time when the user account was created
 * @param updatedAt the date and time when the user account was last updated
 */
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
