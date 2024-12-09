package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken {

    private UUID id;
    private String tokenHash;
    private User user;
    private LocalDateTime expiration;
    private LocalDateTime createdAt;

    public RefreshToken(UUID id, String tokenHash, User user, LocalDateTime expiration, LocalDateTime createdAt) {
        this.id = id;
        this.tokenHash = tokenHash;
        this.user = user;
        this.expiration = expiration;
        this.createdAt = createdAt;
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public String getTokenHash() {
        return this.tokenHash;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDateTime getExpiration() {
        return this.expiration;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class RefreshTokenBuilder {
        private UUID id;
        private String tokenHash;
        private User user;
        private LocalDateTime expiration;
        private LocalDateTime createdAt;

        RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public RefreshTokenBuilder tokenHash(String tokenHash) {
            this.tokenHash = tokenHash;
            return this;
        }

        public RefreshTokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public RefreshTokenBuilder expiration(LocalDateTime expiration) {
            this.expiration = expiration;
            return this;
        }

        public RefreshTokenBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this.id, this.tokenHash, this.user, this.expiration, this.createdAt);
        }
    }
}
