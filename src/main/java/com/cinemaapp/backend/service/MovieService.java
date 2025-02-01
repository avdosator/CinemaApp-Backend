package com.cinemaapp.backend.service;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;

import java.util.UUID;

public interface MovieService {
    Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest);
    Page<Movie> findUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest);
    Movie findById(UUID id);
    Movie createMovie(CreateMovieRequest createMovieRequest, String status);
}
