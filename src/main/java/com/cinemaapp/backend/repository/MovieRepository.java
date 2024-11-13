package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;

import java.util.UUID;

public interface MovieRepository {
    Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest);
    Page<Movie> findUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest);
    Movie findById(UUID id);
}
