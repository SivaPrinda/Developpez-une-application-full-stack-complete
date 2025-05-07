package com.openclassrooms.mddapi.services;


import com.openclassrooms.mddapi.dto.request.LoginUserDTO;
import com.openclassrooms.mddapi.dto.request.RegisterUserDTO;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.Exception.ResponseEntityException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * Registers a new user.
     *
     * @param registerUser The data transfer object containing registration details.
     * @return A JWT token for the newly registered user.
     */
    String register(RegisterUserDTO registerUser);

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The authenticated User entity.
     */
    User getConnectedUser();

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return The User entity.
     * @throws ResponseEntityException If the user is not found.
     */
    User getUser(Long id);

    /**
     * Authenticates a user with their email and password.
     *
     * @param login The login credentials (email and password).
     * @return A JWT token if the login is successful.
     * @throws ResponseEntityException If the credentials are invalid.
     */
    String login(LoginUserDTO login);

    /**
     * Updates the profile information of the currently authenticated user.
     *
     * @param user The updated user entity containing the new profile data.
     * @return The updated User entity.
     */
    User updateUser(User user);
}
