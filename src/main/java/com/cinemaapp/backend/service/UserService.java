package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.auth.AuthRequest;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;

import java.util.UUID;

public interface UserService {

    User createUser(CreateUserRequest createUserRequest);
    User authenticate(AuthRequest authRequest);
    User findByEmail(String email);
    User findById(UUID id);
    User changePassword(ChangePasswordRequest changePasswordRequest);
}
