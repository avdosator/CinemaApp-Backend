package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.RefreshToken;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_token")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RefreshTokenEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "token_hash", nullable = false, length = 128, unique = true)
    private String tokenHash;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public RefreshTokenEntity() {
    }

    public UUID getId() {
        return this.id;
    }

    public String getTokenHash() {
        return this.tokenHash;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
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

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RefreshToken toDomainModel() {
        return RefreshToken.builder()
                .id(this.id)
                .tokenHash(this.tokenHash)
                .user(this.userEntity.toDomainModel())
                .expiration(this.expiration)
                .createdAt(this.createdAt)
                .build();
    }

}
