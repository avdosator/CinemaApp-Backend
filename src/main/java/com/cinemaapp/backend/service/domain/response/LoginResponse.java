package com.cinemaapp.backend.service.domain.response;

import com.cinemaapp.backend.service.domain.model.User;

public class LoginResponse {

    private User user;
    private String jwt;
    private long expiresIn;
    private String refreshToken;

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
