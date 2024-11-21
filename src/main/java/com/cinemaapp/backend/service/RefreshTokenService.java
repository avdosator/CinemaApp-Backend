package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.response.RefreshTokenResponse;

import java.util.UUID;

public interface RefreshTokenService {

    String createRefreshToken(UUID userId);
    RefreshTokenResponse refreshJwt(String token);
    void deleteToken(String token);
}
