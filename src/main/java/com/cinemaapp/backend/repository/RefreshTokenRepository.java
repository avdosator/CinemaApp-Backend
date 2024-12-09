package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.RefreshToken;
import com.cinemaapp.backend.service.domain.request.auth.LogoutRequest;

import java.util.UUID;

public interface RefreshTokenRepository {

    String createRefreshToken(UUID userId);
    RefreshToken validateToken(String token, UUID userId);
    void deleteToken(LogoutRequest logoutRequest);
}
