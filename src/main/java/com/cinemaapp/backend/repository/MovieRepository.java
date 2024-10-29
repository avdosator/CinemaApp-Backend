package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;

import java.util.List;

public interface MovieRepository {
    Page<Movie> findAllMovies(SearchMoviesRequest searchMoviesRequest);
    Page<Movie> findAllActiveMovies(SearchMoviesRequest searchMoviesRequest);
}
