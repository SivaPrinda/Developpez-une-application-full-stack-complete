package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    private UserMapper userMapper;

    /**
     * Retrieves a user by their ID.
     * Endpoint: GET /api/user/{id}
     *
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the UserDTO with user details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(iUserService.getUser(id)));
    }
}
