package com.cinemaapp.backend.service.domain.request.auth;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RefreshTokenRequest {

    @NotNull
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
