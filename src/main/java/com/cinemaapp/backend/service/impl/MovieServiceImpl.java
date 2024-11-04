package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Page<Movie> findMovies(SearchMoviesRequest searchMoviesRequest) {
        return movieRepository.findMovies(searchMoviesRequest);
    }

    @Override
    public Page<Movie> findAllActiveMovies() {
        return movieRepository.findAllActiveMovies();
    }

    @Override
    public Page<Movie> findAllUpcomingMovies() {
        return movieRepository.findAllUpcomingMovies();
    }
}
