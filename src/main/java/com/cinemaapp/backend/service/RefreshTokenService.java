package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.auth.LogoutRequest;
import com.cinemaapp.backend.service.domain.request.auth.RefreshTokenRequest;
import com.cinemaapp.backend.service.domain.response.auth.RefreshTokenResponse;

import java.util.UUID;

public interface RefreshTokenService {

    String createRefreshToken(UUID userId);
    RefreshTokenResponse refreshJwt(RefreshTokenRequest refreshTokenRequest);
    void deleteToken(LogoutRequest logoutRequest);
}
