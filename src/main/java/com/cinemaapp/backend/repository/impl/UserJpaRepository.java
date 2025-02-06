package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.exception.InvalidCredentialsException;
import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.repository.crud.CrudCityRepository;
import com.cinemaapp.backend.repository.crud.CrudPhotoRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.PhotoEntity;
import com.cinemaapp.backend.repository.entity.UserEntity;
import com.cinemaapp.backend.service.domain.model.Photo;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import com.cinemaapp.backend.service.domain.request.UpdateUserRequest;
import com.cinemaapp.backend.service.domain.request.auth.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserJpaRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final CrudCityRepository crudCityRepository;
    private final CrudPhotoRepository crudPhotoRepository;

    @Autowired
    public UserJpaRepository(CrudUserRepository crudUserRepository, PasswordEncoder passwordEncoder, CrudCityRepository crudCityRepository, CrudPhotoRepository crudPhotoRepository) {
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.crudCityRepository = crudCityRepository;
        this.crudPhotoRepository = crudPhotoRepository;
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

    @Override
    public User updateUser(UUID id, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = crudUserRepository.findById(id).orElseThrow();

        // Update fields if present in the request
        if (updateUserRequest.getFirstName() != null) {
            userEntity.setFirstName(updateUserRequest.getFirstName());
        }

        if (updateUserRequest.getLastName() != null) {
            userEntity.setLastName(updateUserRequest.getLastName());
        }

        if (updateUserRequest.getEmail() != null) {
            userEntity.setEmail(updateUserRequest.getEmail());
        }

        if (updateUserRequest.getPhone() != null) {
            userEntity.setPhone(updateUserRequest.getPhone());
        }

        if (updateUserRequest.getCityId() != null) {
            userEntity.setCityEntity(crudCityRepository.findById(updateUserRequest.getCityId()).orElseThrow());
        }

        Photo photo = null;
        if (updateUserRequest.getPhotoUrl() != null) {
            Optional<PhotoEntity> optionalPhotoEntity = Optional.ofNullable(crudPhotoRepository.findPhotoByRefEntityId(updateUserRequest.getUserId()));

             photo = optionalPhotoEntity.map(photoEntity -> {
                photoEntity.setUrl(updateUserRequest.getPhotoUrl());
                photoEntity.setUpdatedAt(LocalDateTime.now());
                return crudPhotoRepository.save(photoEntity).toDomainModel();
            }).orElseGet(() -> createPhoto(updateUserRequest));
        }

        // Save updated user
        User user = crudUserRepository.save(userEntity).toDomainModel();
        if (photo != null) {
            user.setPhoto(photo);
        }
        return user;
    }

    private Photo createPhoto(UpdateUserRequest updateUserRequest) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setRefEntityId(updateUserRequest.getUserId());
        photoEntity.setEntityType("user");
        photoEntity.setUrl(updateUserRequest.getPhotoUrl());
        photoEntity.setCreatedAt(LocalDateTime.now());
        photoEntity.setUpdatedAt(LocalDateTime.now());

        return crudPhotoRepository.save(photoEntity).toDomainModel();
    }
}
