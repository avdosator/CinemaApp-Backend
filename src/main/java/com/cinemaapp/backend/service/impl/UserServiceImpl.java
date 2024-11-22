package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        return userRepository.createUser(createUserRequest);
    }

    @Override
    public User authenticate(CreateUserRequest createUserRequest) {
        return userRepository.findByEmail(createUserRequest.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
