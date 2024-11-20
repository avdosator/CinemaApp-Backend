package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.UserEntity;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        UserEntity userEntity = crudUserRepository.findByEmail(email).orElseThrow();
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
        UserEntity savedUserEntity = crudUserRepository.save(userEntity);
        return userEntity.toDomainModel();
    }
}
