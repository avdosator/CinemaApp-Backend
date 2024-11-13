package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest) {
        return movieRepository.findActiveMovies(searchActiveMoviesRequest);
    }

    @Override
    public Page<Movie> findUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest) {
        return movieRepository.findUpcomingMovies(searchUpcomingMoviesRequest);
    }

    @Override
    public Movie findById(UUID id) {
        return movieRepository.findById(id);
    }
}
