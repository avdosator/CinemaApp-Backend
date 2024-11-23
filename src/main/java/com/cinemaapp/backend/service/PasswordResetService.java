package com.cinemaapp.backend.service;

public interface PasswordResetService {

    String initiatePasswordReset(String email);
}
