package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/movies")
@ControllerAdvice
@Tag(name = "Movies", description = "Endpoints for managing movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get active movies", description = "Retrieve a paginated list of currently active movies.")
    @GetMapping("/active")
    public Page<Movie> getActiveMovies(
            @Parameter(description = "Search criteria for active movies")
            @ModelAttribute SearchActiveMoviesRequest searchActiveMoviesRequest) {
        return movieService.findActiveMovies(searchActiveMoviesRequest);
    }

    @Operation(summary = "Get upcoming movies", description = "Retrieve a paginated list of upcoming movies.")
    @GetMapping("/upcoming")
    public Page<Movie> getUpcomingMovies(
            @Parameter(description = "Search criteria for upcoming movies")
            @ModelAttribute SearchUpcomingMoviesRequest searchUpcomingMoviesRequest) {
        return movieService.findUpcomingMovies(searchUpcomingMoviesRequest);
    }

    @Operation(summary = "Get movie by ID", description = "Retrieve a movie by its unique identifier.")
    @GetMapping("/{id}")
    public Movie getMovieById(
            @Parameter(description = "Unique identifier of the movie")
            @PathVariable UUID id) {
        return movieService.findById(id);
    }

    @Operation(summary = "Create movie", description = "Create movie and its projections")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Movie createMovie(
            @Parameter(description = "Data needed for movie creation")
            @RequestBody @Valid CreateMovieRequest createMovieRequest) {
        return movieService.createMovie(createMovieRequest);
    }
}
