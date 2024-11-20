package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.User;

public interface UserRepository {

    User findByEmail(String email);
}
