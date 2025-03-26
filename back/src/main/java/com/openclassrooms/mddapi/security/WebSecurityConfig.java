package com.openclassrooms.mddapi.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Configures the JwtDecoder bean for decoding and validating JWT tokens.
     */
    @Bean
    public JwtDecoder jwtDecoder(@Value("${application.jwt.key}") String jwtKey) {
        // Define the secret key and the algorithm used for JWT validation.
        SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), 0, jwtKey.getBytes().length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    /**
     * Configures the JwtEncoder bean for encoding JWT tokens.
     */
    @Bean
    public JwtEncoder jwtEncoder(@Value("${application.jwt.key}") String jwtKey) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    /**
     * Configures the security filter chain.
     * This defines security rules for HTTP requests and enables JWT-based security.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for stateless APIs.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management.
                .authorizeHttpRequests(authorize -> authorize
                        // Publicly accessible endpoints.
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/pictures/{id}", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // All other endpoints require authentication.
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // Enable OAuth2 with JWT.
                .build();
    }

    /**
     * Configures the PasswordEncoder bean for password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for secure password hashing.
    }
}