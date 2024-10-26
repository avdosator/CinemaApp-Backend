package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAllMovies();
}
