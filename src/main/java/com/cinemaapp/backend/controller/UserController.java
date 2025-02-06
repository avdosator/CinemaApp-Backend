package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.UpdateUserRequest;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;
import com.cinemaapp.backend.service.domain.response.auth.LoginResponse;
import com.cinemaapp.backend.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthServiceImpl authService;

    @Autowired
    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public LoginResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        return authService.authenticateAndLogin(user, false);
    }

    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping("/change-password")
    public LoginResponse changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        User user = userService.changePassword(changePasswordRequest);
        return authService.authenticateAndLogin(user, true);
    }

    @PatchMapping("/{id}")
    public User updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
