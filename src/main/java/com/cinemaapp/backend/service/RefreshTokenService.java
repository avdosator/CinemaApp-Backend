package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.response.RefreshTokenResponse;

public interface RefreshTokenService {

    String createRefreshToken();
    RefreshTokenResponse refreshJwt(String token);
    void deleteToken(String token);
}
