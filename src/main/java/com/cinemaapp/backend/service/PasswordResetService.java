package com.cinemaapp.backend.service;

public interface PasswordResetService {

    void initiatePasswordReset(String email);
}
