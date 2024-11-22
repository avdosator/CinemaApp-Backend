package com.cinemaapp.backend.repository;

import java.util.UUID;

public interface PasswordResetRepository {

    void saveResetCode(String resetCode, UUID userId);
}
