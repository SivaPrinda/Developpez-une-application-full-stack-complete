package com.openclassrooms.mddapi.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;

/**
 * Security configuration for the web application.
 * This class configures JWT encoding and decoding, password encoding,
 * CORS policy, and the security filter chain. It ensures that authentication
 * is handled using stateless JWT tokens and restricts access to protected resources.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private TokenBlacklist tokenBlacklist;

    /**
     * Configures CORS settings to allow cross-origin requests from the frontend.
     *
     * @return the configured CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // ou configuration.setAllowedOriginPatterns(List.of("http://localhost:*"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configures the JwtDecoder bean for decoding and validating JWT tokens.
     *
     * @param jwtKey the secret key used to validate JWT signatures
     * @return a JwtDecoder instance
     */
    @Bean
    public JwtDecoder jwtDecoder(@Value("${application.jwt.key}") String jwtKey) {
        // Define the secret key and the algorithm used for JWT validation.
        SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), 0, jwtKey.getBytes().length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    /**
     * Configures the JwtEncoder bean for encoding JWT tokens.
     *
     * @param jwtKey the secret key used to sign JWT tokens
     * @return a JwtEncoder instance
     */
    @Bean
    public JwtEncoder jwtEncoder(@Value("${application.jwt.key}") String jwtKey) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    /**
     * Configures the security filter chain.
     *
     * <p>This defines security rules for HTTP requests, including which endpoints
     * are publicly accessible and how authentication is handled via JWT.</p>
     *
     * @param http the HttpSecurity configuration object
     * @return the built SecurityFilterChain
     * @throws Exception if an error occurs during configuration
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
                .addFilterBefore((request, response, chain) -> {
                    String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        if (tokenBlacklist.isBlacklisted(token)) {
                            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return;
                        }
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
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