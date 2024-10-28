package com.cinemaapp.backend.config;

import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.repository.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CrudCityRepository crudCityRepository;
    private final CrudGenreRepository crudGenreRepository;
    private final CrudVenueRepository crudVenueRepository;
    private final CrudMovieRepository crudMovieRepository;
    private final CrudUserRepository crudUserRepository;


    @Autowired
    public DatabaseInitializer(CrudCityRepository crudCityRepository, CrudGenreRepository crudGenreRepository,
                               CrudVenueRepository crudVenueRepository, CrudMovieRepository crudMovieRepository,
                               CrudUserRepository crudUserRepository) {
        this.crudCityRepository = crudCityRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudMovieRepository = crudMovieRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // seed city table
        if (crudCityRepository.findAll().isEmpty()) {
            CityEntity sarajevo = new CityEntity();
            sarajevo.setName("Sarajevo");
            sarajevo.setPostalCode(71000);
            sarajevo.setCountry("Bosnia and Herzegovina");
            sarajevo.setCreatedAt(LocalDateTime.now());
            sarajevo.setUpdatedAt(LocalDateTime.now());

            CityEntity mostar = new CityEntity();
            mostar.setName("Mostar");
            mostar.setPostalCode(88000);
            mostar.setCountry("Bosnia and Herzegovina");
            mostar.setCreatedAt(LocalDateTime.now());
            mostar.setUpdatedAt(LocalDateTime.now());

            crudCityRepository.saveAll(Arrays.asList(sarajevo, mostar));
        }

        
    }
}
