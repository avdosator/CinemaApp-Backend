package com.cinemaapp.backend.config;

import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.repository.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (crudCityRepository.findAll().isEmpty() && crudUserRepository.findAll().isEmpty() &&
                crudGenreRepository.findAll().isEmpty() && crudMovieRepository.findAll().isEmpty() &&
                crudVenueRepository.findAll().isEmpty()) {

            // seed city table
            CityEntity sarajevo = new CityEntity();
            CityEntity mostar = new CityEntity();

            sarajevo.setName("Sarajevo");
            sarajevo.setPostalCode(71000);
            sarajevo.setCountry("Bosnia and Herzegovina");
            sarajevo.setCreatedAt(LocalDateTime.now());
            sarajevo.setUpdatedAt(LocalDateTime.now());

            mostar.setName("Mostar");
            mostar.setPostalCode(88000);
            mostar.setCountry("Bosnia and Herzegovina");
            mostar.setCreatedAt(LocalDateTime.now());
            mostar.setUpdatedAt(LocalDateTime.now());

            crudCityRepository.saveAll(Arrays.asList(sarajevo, mostar));

            // seed users table
            UserEntity user = new UserEntity();

            user.setFirstName("Avdo");
            user.setLastName("Sator");
            user.setEmail("avdo.sator@hotmail.com");
            user.setPasswordHash("hashed_password");
            user.setPhone("+38762183628");
            user.setCityEntity(sarajevo);
            user.setRole("ROLE_USER");
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            crudUserRepository.save(user);
            
            System.out.println("Database seeded successfully");
        }
    }
}
