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
    public void initiatePasswordReset(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with this email does not exist.");
        }
        // Generate string from 0000 to 9999
        SecureRandom secureRandom = new SecureRandom();
        String resetCode = String.format("%04d", secureRandom.nextInt(10000));

        passwordResetRepository.saveResetCode(resetCode, user.getId());
    }
}
