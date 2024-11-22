package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CrudPasswordResetRepository extends JpaRepository<PasswordResetEntity, UUID> {

    Optional<PasswordResetEntity> findByUserId(UUID userId);
    Optional<PasswordResetEntity> findByResetCodeAndUserId(String resetCode, UUID userId);
    void deleteByUserId(UUID userId);
}
