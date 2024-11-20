package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.UserEntity;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserJpaRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserJpaRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = crudUserRepository.findByEmail(email).orElseThrow();
        return userEntity.toDomainModel();
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        return null;
    }
}
