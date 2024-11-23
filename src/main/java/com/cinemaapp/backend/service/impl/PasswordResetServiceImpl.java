package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.PasswordResetRepository;
import com.cinemaapp.backend.service.PasswordResetService;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserService userService;
    private final PasswordResetRepository passwordResetRepository;

    @Autowired
    public PasswordResetServiceImpl(UserService userService, PasswordResetRepository passwordResetRepository) {
        this.userService = userService;
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public String initiatePasswordReset(String email) {

        User user = userService.findByEmail(email);

        // Generate string from 0000 to 9999
        SecureRandom secureRandom = new SecureRandom();
        String resetCode = String.format("%04d", secureRandom.nextInt(10000));
        String savedResetCode = passwordResetRepository.saveResetCode(resetCode, user.getId());

        String maskedEmail = maskEmail(user.getEmail());
        return "We have sent code to your email " + maskedEmail + ". Please, enter the code below to verify.";
    }

    public String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        String[] parts = email.split("@");
        String localPart = parts[0];
        String domain = parts[1];

        if (localPart.length() < 3) {
            // For very short local parts, mask the middle entirely
            return localPart.charAt(0) + "*".repeat(localPart.length() - 1) + "@" + domain;
        }

        // Mask all characters except the first and last
        String maskedLocal = localPart.charAt(0)
                + "*".repeat(localPart.length() - 2)
                + localPart.charAt(localPart.length() - 1);

        return maskedLocal + "@" + domain;
    }
}
