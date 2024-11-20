package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        return userRepository.createUser(createUserRequest);
    }

    @Override
    public User authenticate(CreateUserRequest createUserRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createUserRequest.getEmail(),
                        createUserRequest.getPassword()));

        return userRepository.findByEmail(createUserRequest.getEmail());
    }
}
