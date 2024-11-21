package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.Size;

public class RefreshTokenRequest {

    @Size(min = 88, max = 88)
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
