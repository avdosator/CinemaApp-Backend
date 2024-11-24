package com.cinemaapp.backend.repository;

import java.util.UUID;

public interface PasswordResetRepository {

    String saveResetCode(String resetCode, UUID userId);
    boolean verifyResetCode(String resetCode, UUID userId);
}
