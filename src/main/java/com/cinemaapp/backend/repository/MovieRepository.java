package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchDraftMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import com.cinemaapp.backend.service.domain.response.MovieRatingsResponse;

import java.util.UUID;

public interface MovieRepository {
    Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest);
    Page<Movie> findUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest);
    Movie findById(UUID id);
    Movie createMovie(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status);

    Page<Movie> findDraftMovies(SearchDraftMoviesRequest searchDraftMoviesRequest);

    Page<Movie> findArchivedMovies(SearchDraftMoviesRequest searchDraftMoviesRequest);

    void archiveMovie(UUID id);

    void moveToDrafts(UUID id);
}
