package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudHallRepository extends JpaRepository<HallEntity, UUID> {

    HallEntity findByName(String name);
}
