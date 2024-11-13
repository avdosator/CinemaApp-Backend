package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Page<Movie> getActiveMovies(@ModelAttribute SearchActiveMoviesRequest searchActiveMoviesRequest) {
        return movieService.findActiveMovies(searchActiveMoviesRequest);
    }

    @GetMapping("/upcoming")
    public Page<Movie> getUpcomingMovies(@ModelAttribute SearchUpcomingMoviesRequest searchUpcomingMoviesRequest) {
        return movieService.findUpcomingMovies(searchUpcomingMoviesRequest);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable UUID id) {
        return movieService.findById(id);
    }
}
