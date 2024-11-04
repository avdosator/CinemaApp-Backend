package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;

public interface MovieRepository {
    Page<Movie> findMovies(SearchMoviesRequest searchMoviesRequest);
    Page<Movie> findAllActiveMovies();
    Page<Movie> findAllUpcomingMovies();
}
