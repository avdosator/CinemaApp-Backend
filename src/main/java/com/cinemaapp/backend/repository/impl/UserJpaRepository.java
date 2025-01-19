package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.exception.InvalidCredentialsException;
import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.UserEntity;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class UserJpaRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserJpaRepository(CrudUserRepository crudUserRepository, PasswordEncoder passwordEncoder) {
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = crudUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist."));
        return userEntity.toDomainModel();
    }

    @Override
    public User findById(UUID id) {
        UserEntity userEntity = crudUserRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist."));
        return userEntity.toDomainModel();
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPasswordHash(passwordEncoder.encode(createUserRequest.getPassword()));
        userEntity.setRole("ROLE_USER");
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        UserEntity savedUserEntity;
        try {
            savedUserEntity = crudUserRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException(createUserRequest.getEmail() + " email is already taken.");
        }
        return savedUserEntity.toDomainModel();
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        UserEntity userEntity = crudUserRepository.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        userEntity.setPasswordHash(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        UserEntity updatedUser;
        try {
            updatedUser = crudUserRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException(e.getMessage());
        }
        return updatedUser.toDomainModel();
    }
}
