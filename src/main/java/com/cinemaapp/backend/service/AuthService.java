package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.response.auth.LoginResponse;

public interface AuthService {

    LoginResponse authenticateAndLogin(User authenticatedUser, boolean isRememberMe);
}
