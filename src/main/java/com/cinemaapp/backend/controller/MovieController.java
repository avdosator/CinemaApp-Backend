package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam SearchMoviesRequest searchMoviesRequest) {
        List<Movie> movieList = movieService.findAllMovies(searchMoviesRequest);
        if(movieList.isEmpty()) {
            return Collections.emptyList();
        }
        return movieList;
    }
}
