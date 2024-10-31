package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.City;

import java.util.List;

public interface CityService {

    List<City> findAllCities();
}
