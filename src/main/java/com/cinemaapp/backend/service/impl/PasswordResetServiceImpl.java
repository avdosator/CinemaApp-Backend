package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.PasswordResetRepository;
import com.cinemaapp.backend.service.EmailService;
import com.cinemaapp.backend.service.PasswordResetService;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.EmailDetailsRequest;
import com.cinemaapp.backend.service.domain.request.auth.VerifyResetCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserService userService;
    private final PasswordResetRepository passwordResetRepository;
    private final EmailService emailService;

    @Autowired
    public PasswordResetServiceImpl(UserService userService,
                                    PasswordResetRepository passwordResetRepository,
                                    EmailService emailService) {
        this.userService = userService;
        this.passwordResetRepository = passwordResetRepository;
        this.emailService = emailService;
    }

    @Override
    public String initiatePasswordReset(String email) {

        User user = userService.findByEmail(email);

        // Generate string from 0000 to 9999
        SecureRandom secureRandom = new SecureRandom();
        String resetCode = String.format("%04d", secureRandom.nextInt(10000));
        String savedResetCode = passwordResetRepository.saveResetCode(resetCode, user.getId());

        emailService.sendResetCode(generateEmailDetailsRequest(user.getEmail(), savedResetCode));
        String maskedEmail = maskEmail(user.getEmail());
        return "We have sent code to your email " + maskedEmail + ". Please, enter the code below to verify.";
    }

    @Override
    public boolean verifyResetCode(VerifyResetCodeRequest verifyResetCodeRequest) {
        User user = userService.findByEmail(verifyResetCodeRequest.getEmail());
        return passwordResetRepository.verifyResetCode(verifyResetCodeRequest.getResetCode(), user.getId());
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

    private EmailDetailsRequest generateEmailDetailsRequest(String email, String resetCode) {
        EmailDetailsRequest request = new EmailDetailsRequest();
        request.setTo(email);
        request.setSubject("Password Reset Code");
        request.setBody("""
                <html>
                    <body>
                        <p>Dear User,</p>
                        <p>Your code for password reset is: <strong>%s</strong>. It will be valid for 10 minutes from now.</p>
                        <p>If you didn’t request this, please ignore this email.</p><br>
                        <p>Best regards,<br><br>Cinema App</p>
                    </body>
                </html>
                """.formatted(resetCode));
        return request;
    }
}
