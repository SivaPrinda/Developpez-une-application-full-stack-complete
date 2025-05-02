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


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        User updatedUser = iUserService.updateUser(user);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

}
