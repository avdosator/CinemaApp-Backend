package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;

public interface UserRepository {

    User findByEmail(String email);
    User createUser(CreateUserRequest createUserRequest);
}
