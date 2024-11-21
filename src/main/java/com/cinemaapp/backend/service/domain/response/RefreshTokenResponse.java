package com.cinemaapp.backend.service.domain.response;

public class RefreshTokenResponse {

    private String jwt;
    private long expiresIn;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
