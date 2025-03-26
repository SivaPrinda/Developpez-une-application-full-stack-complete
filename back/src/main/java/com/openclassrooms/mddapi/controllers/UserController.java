package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.UserDTO;
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

    /**
     * Retrieves a user by their ID.
     * Endpoint: GET /api/user/{id}
     *
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the UserDTO with user details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToUserDTO(iUserService.getUser(id)));
    }

    /**
     * Converts a User entity to a UserDTO object.
     * Used to structure and expose user data in a safe format.
     *
     * @param user the User entity to convert.
     * @return a UserDTO containing the user's details.
     */
    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
