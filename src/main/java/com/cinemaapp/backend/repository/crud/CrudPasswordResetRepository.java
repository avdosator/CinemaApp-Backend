package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CrudPasswordResetRepository extends JpaRepository<PasswordResetEntity, UUID> {

    Optional<PasswordResetEntity> findByResetCodeAndUserId(String resetCode, UUID userId);

    @Modifying
    @Transactional
    int deleteByExpirationTimeBefore(LocalDateTime now);
}
