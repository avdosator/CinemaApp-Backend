package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.*;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.*;
import com.cinemaapp.backend.service.domain.response.LoginResponse;
import com.cinemaapp.backend.service.domain.response.RefreshTokenResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordResetService passwordResetService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService,
                          RefreshTokenService refreshTokenService,
                          PasswordResetService passwordResetService,
                          AuthService authService
    ) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.passwordResetService = passwordResetService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User authenticatedUser = userService.authenticate(createUserRequest);
        return authService.authenticateAndLogin(authenticatedUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        refreshTokenService.deleteToken(logoutRequest.getRefreshToken());
        return ResponseEntity.ok("token deleted");
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshJwt(@Valid @RequestBody RefreshTokenRequest refreshToken) {
        return refreshTokenService.refreshJwt(refreshToken.getRefreshToken());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        String response = passwordResetService.initiatePasswordReset(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<String> verifyResetCode(@Valid @RequestBody VerifyResetCodeRequest verifyResetCodeRequest) {
        boolean isValid = passwordResetService.verifyResetCode(verifyResetCodeRequest);
        if (isValid) {
            return ResponseEntity.ok("Code is valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset code");
        }
    }
}
