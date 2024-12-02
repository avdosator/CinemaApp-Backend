package com.cinemaapp.backend.service.domain.request.auth;

import jakarta.validation.constraints.Size;

public class LogoutRequest {
    @Size(min = 86, max = 86)
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
