package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudUserRepository extends JpaRepository<UserEntity, UUID> {
}
