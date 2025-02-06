package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.RefreshTokenEntity;
import com.cinemaapp.backend.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByTokenHash(String tokenHash);

    List<RefreshTokenEntity> findByUserEntity_Id(UUID userId);

    @Modifying
    @Transactional
    int deleteByExpirationBefore(LocalDateTime now);

    @Modifying
    @Transactional
    void deleteByUserEntity(UserEntity userEntity);
}
