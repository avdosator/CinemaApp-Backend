package com.cinemaapp.backend.config;

import com.cinemaapp.backend.repository.CityRepository;
import com.cinemaapp.backend.repository.GenreRepository;
import com.cinemaapp.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CityRepository cityRepository;
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public DatabaseInitializer(CityRepository cityRepository, GenreRepository genreRepository, MovieRepository movieRepository) {
        this.cityRepository = cityRepository;
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
