package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.CityRepository;
import com.cinemaapp.backend.service.CityService;
import com.cinemaapp.backend.service.domain.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAllCities();
    }
}
