package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.CityRepository;
import com.cinemaapp.backend.repository.crud.CrudCityRepository;
import com.cinemaapp.backend.repository.entity.CityEntity;
import com.cinemaapp.backend.service.domain.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityJpaRepository implements CityRepository {

    private final CrudCityRepository crudCityRepository;

    @Autowired
    public CityJpaRepository(CrudCityRepository crudCityRepository) {
        this.crudCityRepository = crudCityRepository;
    }

    @Override
    public List<City> findAllCities() {
        List<CityEntity> cityEntities = crudCityRepository.findAll();
        List<City> cities = new ArrayList<>();
        for (CityEntity cityEntity : cityEntities) {
            cities.add(cityEntity.toDomainModel());
        }
        return cities;
    }
}
