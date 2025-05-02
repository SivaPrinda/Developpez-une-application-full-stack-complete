package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.dto.request.LoginUserDTO;
import com.openclassrooms.mddapi.dto.request.RegisterUserDTO;
import com.openclassrooms.mddapi.dto.response.TokenDTO;
import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
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
     * Registers a new user.
     * Endpoint: POST /api/auth/register
     *
     * @param registerUser the user details sent in the request body.
     * @return a ResponseEntity containing a TokenDTO (the authentication token).
     */
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> signIn(@Valid @RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.register(registerUser)));
    }

    /**
     * Logs in an existing user.
     * Endpoint: POST /api/auth/login
     *
     * @param loginUser the login credentials sent in the request body.
     * @return a ResponseEntity containing a TokenDTO (the authentication token).
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> signUp(@RequestBody LoginUserDTO loginUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.login(loginUser)));
    }

    /**
     * Retrieves the currently logged-in user's information.
     * Endpoint: GET /api/auth/me
     *
     * @return a ResponseEntity containing a UserDTO (user details).
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getConnectedUser() {
        return ResponseEntity.ok(userMapper.toDto(iUserService.getConnectedUser()));
    }
}
