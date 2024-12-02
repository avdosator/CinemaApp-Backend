package com.cinemaapp.backend.service.domain.request.auth;

import jakarta.validation.constraints.Email;

public class ForgotPasswordRequest {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
