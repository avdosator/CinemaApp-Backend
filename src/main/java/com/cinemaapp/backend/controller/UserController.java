package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.JwtService;
import com.cinemaapp.backend.service.RefreshTokenService;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public UserController(UserService userService,
                          JwtService jwtService,
                          UserDetailsService userDetailsService,
                          RefreshTokenService refreshTokenService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User authenticatedUser = userService.authenticate(createUserRequest);
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(authenticatedUser.getEmail()));
        String refreshToken = refreshTokenService.createRefreshToken();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setRefreshToken(refreshToken);
        return loginResponse;
    }




}
