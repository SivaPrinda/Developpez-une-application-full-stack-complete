package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.LoginUserDTO;
import com.openclassrooms.mddapi.dto.request.RegisterUserDTO;
import com.openclassrooms.mddapi.dto.response.TokenDTO;
import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Handles user registration.
     * Endpoint: POST /api/auth/register
     *
     * @param registerUser DTO containing user registration details.
     * @return a ResponseEntity containing a TokenDTO with the JWT token.
     */
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> signIn(@Valid @RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.register(registerUser)));
    }

    /**
     * Handles user login.
     * Endpoint: POST /api/auth/login
     *
     * @param loginUser DTO containing user login credentials.
     * @return a ResponseEntity containing a TokenDTO with the JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> signUp(@RequestBody LoginUserDTO loginUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.login(loginUser)));
    }

    /**
     * Retrieves the currently authenticated user's information.
     * Endpoint: GET /api/auth/me
     *
     * @return a ResponseEntity containing the UserDTO of the authenticated user.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getConnectedUser() {
        return ResponseEntity.ok(userMapper.toDto(iUserService.getConnectedUser()));
    }
}
