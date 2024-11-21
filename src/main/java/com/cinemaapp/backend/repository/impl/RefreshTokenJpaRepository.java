package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.exception.TokenNotFoundException;
import com.cinemaapp.backend.repository.RefreshTokenRepository;
import com.cinemaapp.backend.repository.crud.CrudRefreshTokenRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.RefreshTokenEntity;
import com.cinemaapp.backend.service.domain.model.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Repository
public class RefreshTokenJpaRepository implements RefreshTokenRepository {

    private static final int TOKEN_LENGTH = 64;
    private static final int TOKEN_DURATION = 14; //days

    private final CrudRefreshTokenRepository crudRefreshTokenRepository;
    private final CrudUserRepository crudUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RefreshTokenJpaRepository(CrudRefreshTokenRepository crudRefreshTokenRepository,
                                     CrudUserRepository crudUserRepository,
                                     PasswordEncoder passwordEncoder) {
        this.crudRefreshTokenRepository = crudRefreshTokenRepository;
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createRefreshToken(UUID userId) {
        byte[] randomBytes = new byte[TOKEN_LENGTH];
        new SecureRandom().nextBytes(randomBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        String tokenHash = passwordEncoder.encode(token);

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setTokenHash(tokenHash);
        refreshTokenEntity.setUserEntity(crudUserRepository.findById(userId).orElseThrow());
        refreshTokenEntity.setExpiration(LocalDateTime.now().plusDays(TOKEN_DURATION));
        refreshTokenEntity.setCreatedAt(LocalDateTime.now());
        crudRefreshTokenRepository.save(refreshTokenEntity);
        return token;
    }

    @Override
    public RefreshToken validateToken(String token, UUID userId) {
        return crudRefreshTokenRepository.findByUserEntity_Id(userId).stream()
                .filter(entity -> passwordEncoder.matches(token, entity.getTokenHash()))
                .findFirst()
                .map(RefreshTokenEntity::toDomainModel)
                .orElseThrow(() -> new RuntimeException("Invalid or expired refresh token!"));
    }

    @Override
    public void deleteToken(String token, UUID userId) {
        List<RefreshTokenEntity> tokens = crudRefreshTokenRepository.findByUserEntity_Id(userId);

        // Filter and find the matching token based on the hash
        RefreshTokenEntity matchingToken = tokens.stream()
                .filter(entity -> passwordEncoder.matches(token, entity.getTokenHash()))
                .findFirst()
                .orElseThrow(() -> new TokenNotFoundException("Token not found or already deleted"));

        // Delete the matching token
        crudRefreshTokenRepository.delete(matchingToken);
    }
}
