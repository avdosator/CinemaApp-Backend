package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.PasswordResetRepository;
import com.cinemaapp.backend.repository.crud.CrudPasswordResetRepository;
import com.cinemaapp.backend.repository.entity.PasswordResetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PasswordResetJpaRepository implements PasswordResetRepository {

    private final CrudPasswordResetRepository crudPasswordResetRepository;

    @Autowired
    public PasswordResetJpaRepository(CrudPasswordResetRepository crudPasswordResetRepository) {
        this.crudPasswordResetRepository = crudPasswordResetRepository;
    }

    @Override
    public String saveResetCode(String resetCode, UUID userId) {
        PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
        passwordResetEntity.setResetCode(resetCode);
        passwordResetEntity.setUserId(userId);
        passwordResetEntity.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        passwordResetEntity.setCreatedAt(LocalDateTime.now());
        PasswordResetEntity savedEntity = crudPasswordResetRepository.save(passwordResetEntity);
        return savedEntity.getResetCode();
    }

    @Override
    public boolean verifyResetCode(String resetCode, UUID userId) {
        Optional<PasswordResetEntity> optionalResetEntity =
                crudPasswordResetRepository.findByResetCodeAndUserId(resetCode, userId);

        if (optionalResetEntity.isPresent()) {
            PasswordResetEntity entity = optionalResetEntity.get();
            if (entity.getExpirationTime().isAfter(LocalDateTime.now())) {
                crudPasswordResetRepository.delete(entity); // Delete immediately after validation
                return true; // Validation successful
            }
        }
        return false; // Code is invalid or expired
    }
}
