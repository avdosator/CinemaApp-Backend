package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
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
    public Page<Movie> getAllMovies(@RequestParam SearchMoviesRequest searchMoviesRequest) {
        Page<Movie> movies = movieService.findAllMovies(searchMoviesRequest);
        if(movies.isEmpty()) {
            Page<Movie> emptyPage = new Page<>();
            emptyPage.setContent(Collections.emptyList());
            emptyPage.setPageNumber(0);
            emptyPage.setPageSize(0);
            emptyPage.setTotalElements(0);
            emptyPage.setTotalPages(0);
            return emptyPage;
        }
        return movies;
    }
}
