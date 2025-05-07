package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * REST controller for handling user-related operations.
 * Provides an endpoint to update user profile information.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Updates the authenticated user's profile.
     *
     * @param user the user entity containing updated profile information.
     * @return the updated user information as a UserDTO wrapped in a ResponseEntity.
     */
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        User updatedUser = iUserService.updateUser(user);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

}
