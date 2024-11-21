package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.config.security.CustomUserDetails;
import com.cinemaapp.backend.exception.TokenExpiredException;
import com.cinemaapp.backend.repository.RefreshTokenRepository;
import com.cinemaapp.backend.service.JwtService;
import com.cinemaapp.backend.service.RefreshTokenService;
import com.cinemaapp.backend.service.domain.model.RefreshToken;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.response.RefreshTokenResponse;
import com.cinemaapp.backend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository,
                                   JwtService jwtService,
                                   UserDetailsService userDetailsService
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String createRefreshToken() {
        User user = UserUtils.getCurrentUser();
        return refreshTokenRepository.createRefreshToken(user.getId());
    }

    @Override
    public RefreshTokenResponse refreshJwt(String token) {
        RefreshToken refreshToken = refreshTokenRepository.validateToken(token);

        if (refreshToken.getExpiration().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Refresh token has expired!");
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(refreshToken.getUser().getEmail());
        String jwt = jwtService.generateToken(customUserDetails);
        return new RefreshTokenResponse(jwt, jwtService.getExpirationTime());

    }

    @Override
    public void deleteToken(String token) {

    }
}
