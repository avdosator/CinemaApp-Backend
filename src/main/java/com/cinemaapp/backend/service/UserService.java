package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.ChangePasswordRequest;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;

public interface UserService {

    User createUser(CreateUserRequest createUserRequest);
    User authenticate(CreateUserRequest createUserRequest);
    User findByEmail(String email);
    User changePassword(ChangePasswordRequest changePasswordRequest);
}
