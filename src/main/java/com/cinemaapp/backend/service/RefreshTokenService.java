package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.RefreshToken;

public interface RefreshTokenService {

    String createRefreshToken();
    RefreshToken validateToken(String token);
    void deleteToken(String token);
}
