package com.openclassrooms.mddapi.services;

/**
 * Service interface for handling JWT operations.
 *
 * <p>This interface provides methods related to the creation of JSON Web Tokens (JWT)
 * for authenticated users in the context of Spring Security.</p>
 */
import org.springframework.security.core.Authentication;

public interface IJWTService {
    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param authentication The Authentication object containing user details.
     * @return A JWT token as a String.
     */
    String generateToken(Authentication authentication);
}