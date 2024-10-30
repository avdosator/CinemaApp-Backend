package com.cinemaapp.backend.service;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;

import java.util.List;

public interface MovieService {
    Page<Movie> findAllMovies(SearchMoviesRequest searchMoviesRequest);
    Page<Movie> findAllActiveMovies(SearchMoviesRequest searchMoviesRequest);
    Page<Movie> findAllUpcomingMovies(SearchMoviesRequest searchMoviesRequest);
}
