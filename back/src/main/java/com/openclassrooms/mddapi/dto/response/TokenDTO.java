package com.openclassrooms.mddapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for returning an authentication token to the client.
 *
 * <p>This record is used in login or registration responses to provide the
 * JWT token used for authenticating subsequent requests.</p>
 *
 * @param token the JWT used to authenticate user requests
 */
public record TokenDTO(
    @Schema(description = "Token used to authenticate user")
    String token
) {
}