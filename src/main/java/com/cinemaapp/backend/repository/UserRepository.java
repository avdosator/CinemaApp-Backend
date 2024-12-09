package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;

import java.util.UUID;

public interface UserRepository {

    User findByEmail(String email);
    User findById(UUID id);
    User createUser(CreateUserRequest createUserRequest);
    User changePassword(ChangePasswordRequest changePasswordRequest);
}
