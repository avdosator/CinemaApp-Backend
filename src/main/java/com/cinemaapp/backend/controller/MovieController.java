package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@ControllerAdvice
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/active")
    public Page<Movie> getActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest) {
        return movieService.findActiveMovies(searchActiveMoviesRequest);
    }

    @GetMapping("/upcoming")
    public Page<Movie> getUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest) {
        return movieService.findUpcomingMovies(searchUpcomingMoviesRequest);
    }
}
