package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudCityRepository extends JpaRepository<CityEntity, UUID> {

    CityEntity findByName(String name);
}
