package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.exception.InvalidCredentialsException;
import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.service.UserService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.UpdateUserRequest;
import com.cinemaapp.backend.service.domain.request.auth.AuthRequest;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    public User authenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    ));
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid email or password, please try again.");
        }

        return userRepository.findByEmail(authRequest.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        return userRepository.changePassword(changePasswordRequest);
    }

    @Transactional
    @Override
    public User updateUser(UUID id, UpdateUserRequest updateUserRequest) {
        return userRepository.updateUser(id, updateUserRequest);
    }
}
