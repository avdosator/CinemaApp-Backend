package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByTokenHash(String tokenHash);
    List<RefreshTokenEntity> findByUserEntity_Id(UUID userId);
    void deleteByTokenHash(String tokenHash);
}
