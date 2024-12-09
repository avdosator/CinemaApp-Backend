package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.AuthService;
import com.cinemaapp.backend.service.JwtService;
import com.cinemaapp.backend.service.RefreshTokenService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.response.auth.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthServiceImpl(JwtService jwtService,
                           RefreshTokenService refreshTokenService,
                           UserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public LoginResponse authenticateAndLogin(User authenticatedUser, boolean isRememberMe) {
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(authenticatedUser.getEmail()));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        if (isRememberMe) {
            String refreshToken = refreshTokenService.createRefreshToken(authenticatedUser.getId());
            loginResponse.setRefreshToken(refreshToken);
        }
        return loginResponse;
    }
}
