package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.config.security.CustomUserDetails;
import com.cinemaapp.backend.repository.RefreshTokenRepository;
import com.cinemaapp.backend.service.RefreshTokenService;
import com.cinemaapp.backend.service.domain.model.RefreshToken;
import com.cinemaapp.backend.service.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String createRefreshToken() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        return refreshTokenRepository.createRefreshToken(user.getId());
    }

    @Override
    public RefreshToken validateToken(String token) {
        return null;
    }

    @Override
    public void deleteToken(String token) {

    }
}
