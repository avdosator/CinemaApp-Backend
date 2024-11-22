package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.JwtService;
import com.cinemaapp.backend.service.RefreshTokenService;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.LogoutRequest;
import com.cinemaapp.backend.service.domain.request.RefreshTokenRequest;
import com.cinemaapp.backend.service.domain.response.LoginResponse;
import com.cinemaapp.backend.service.domain.response.RefreshTokenResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(UserService userService,
                          JwtService jwtService,
                          UserDetailsService userDetailsService,
                          RefreshTokenService refreshTokenService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User authenticatedUser = userService.authenticate(createUserRequest);
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(authenticatedUser.getEmail()));
        String refreshToken = refreshTokenService.createRefreshToken(authenticatedUser.getId());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setRefreshToken(refreshToken);
        return loginResponse;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        refreshTokenService.deleteToken(logoutRequest.getRefreshToken());
        return ResponseEntity.ok("token deleted");
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshJwt(@Valid @RequestBody RefreshTokenRequest refreshToken) {
        return refreshTokenService.refreshJwt(refreshToken.getRefreshToken());
    }
}
