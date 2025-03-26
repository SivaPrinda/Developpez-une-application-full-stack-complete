package com.openclassrooms.mddapi.services;

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