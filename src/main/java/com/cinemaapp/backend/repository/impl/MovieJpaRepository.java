package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.repository.crud.CrudMovieRepository;
import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.service.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository) {
        this.crudMovieRepository = crudMovieRepository;
    }

    @Override
    public List<Movie> findAllMovies() {
        List<MovieEntity> movieEntities = crudMovieRepository.findAll();
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(movieEntity.toDomainModel());
        }
        return movies;
    }
}
