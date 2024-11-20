package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.RefreshToken;

import java.util.UUID;

public interface RefreshTokenRepository {

    String createRefreshToken(UUID userId);
    RefreshToken validateToken(String token);
    void deleteToken(String token);
}
