package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.VerifyResetCodeRequest;

public interface PasswordResetService {

    String initiatePasswordReset(String email);
    boolean verifyResetCode(VerifyResetCodeRequest verifyResetCodeRequest);
}
