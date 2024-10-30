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

    @GetMapping
    public Page<Movie> getAllMovies(@ModelAttribute SearchMoviesRequest searchMoviesRequest) {
        Page<Movie> movies = movieService.findAllMovies(searchMoviesRequest);
        if(movies.isEmpty()) {
            return new Page<>();
        }
        return movies;
    }

    @GetMapping("/active")
    public Page<Movie> getAllActiveMovies(@ModelAttribute SearchMoviesRequest searchMoviesRequest) {
        Page<Movie> activeMovies = movieService.findAllActiveMovies(searchMoviesRequest);
        if(activeMovies.isEmpty()) {
            return new Page<Movie>();
        }
        return activeMovies;
    }

    @GetMapping("/upcoming")
    public Page<Movie> getAllUpcomingMovies(@ModelAttribute SearchMoviesRequest searchMoviesRequest) {
        Page<Movie> upcomingMovies = movieService.findAllUpcomingMovies(searchMoviesRequest);
        if(upcomingMovies.isEmpty()) {
            return new Page<Movie>();
        }
        return upcomingMovies;
    }
}
