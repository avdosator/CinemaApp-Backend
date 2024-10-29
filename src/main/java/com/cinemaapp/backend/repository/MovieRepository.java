package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAllMovies(SearchMoviesRequest searchMoviesRequest);
}
