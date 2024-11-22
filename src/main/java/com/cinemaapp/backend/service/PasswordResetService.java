package com.cinemaapp.backend.service;

public interface PasswordResetService {

    void validateEmailForPasswordReset(String email);
}
