package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> findAllCities();
}
