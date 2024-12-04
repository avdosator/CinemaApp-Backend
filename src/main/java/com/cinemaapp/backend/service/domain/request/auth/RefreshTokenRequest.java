package com.cinemaapp.backend.service.domain.request.auth;

import jakarta.validation.constraints.Size;

import java.util.UUID;

public class RefreshTokenRequest {

    @Size(min = 86, max = 86)
    private String refreshToken;

    private UUID userId;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
