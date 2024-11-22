package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.CityService;
import com.cinemaapp.backend.service.domain.model.City;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@Tag(name = "Cities", description = "Endpoints for managing cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(summary = "Get all cities", description = "Retrieve a list of all cities with at least one venue.")
    @GetMapping
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }
}
