package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.response.LoginResponse;
import com.cinemaapp.backend.service.domain.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthService(JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       UserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    public LoginResponse authenticateAndLogin(User authenticatedUser) {
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(authenticatedUser.getEmail()));
        String refreshToken = refreshTokenService.createRefreshToken(authenticatedUser.getId());

        SignUpResponse signUpResponse ) new SignUpResponse()
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(authenticatedUser);
        loginResponse.setJwt(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setRefreshToken(refreshToken);

        return loginResponse;
    }
}
