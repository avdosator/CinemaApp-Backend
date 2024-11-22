package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.Size;

public class RefreshTokenRequest {

    @Size(min = 86, max = 86)
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
