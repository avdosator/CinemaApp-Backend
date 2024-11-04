package com.cinemaapp.backend.service;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;

public interface MovieService {
    Page<Movie> findMovies(SearchMoviesRequest searchMoviesRequest);
    Page<Movie> findAllActiveMovies();
    Page<Movie> findAllUpcomingMovies();
}
