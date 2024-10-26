package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAllMovies();
}
