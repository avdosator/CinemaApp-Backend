package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.service.MovieRatingService;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.UploadcareService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchDraftMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import com.cinemaapp.backend.service.domain.response.MovieRatingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieRatingService movieRatingService;
    private final UploadcareService uploadcareService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieRatingService movieRatingService,
                            UploadcareService uploadcareService) {
        this.movieRepository = movieRepository;
        this.movieRatingService = movieRatingService;
        this.uploadcareService = uploadcareService;
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
    public Page<Movie> findDraftMovies(SearchDraftMoviesRequest searchDraftMoviesRequest) {
        return movieRepository.findDraftMovies(searchDraftMoviesRequest);
    }

    @Override
    public Movie findById(UUID id) {
        return movieRepository.findById(id);
    }

    @Override
    @Transactional
    public Movie createMovie(CreateMovieRequest createMovieRequest, String status) {
        // Step 1: Verify photo URLs -> It works without this
        /*for (String photoUrl : createMovieRequest.getPhotoUrls()) {
            boolean isValid = uploadcareService.verifyPhotoUrl(photoUrl);
            if (!isValid) {
                throw new IllegalArgumentException("Invalid photo URL: " + photoUrl);
            }
        }*/
        MovieRatingsResponse movieRatingsResponse = movieRatingService.getMovieRatings(createMovieRequest.getTitle());
        validateRequestFields(createMovieRequest, movieRatingsResponse, status);
        return movieRepository.createMovie(createMovieRequest, movieRatingsResponse, status);
    }

    private void validateRequestFields(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        if (status.equals("draft-1")) {
            validateDraft1Fields(createMovieRequest, movieRatingsResponse);
        } else if (status.equals("draft-2")) {
            validateDraft1Fields(createMovieRequest, movieRatingsResponse);
            validateDraft2Fields(createMovieRequest, movieRatingsResponse);
        } else {
            validateDraft1Fields(createMovieRequest, movieRatingsResponse);
            validateDraft2Fields(createMovieRequest, movieRatingsResponse);
            validateDraft3Fields(createMovieRequest, movieRatingsResponse);
        }
    }

    private void validateDraft3Fields(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse) {
        if (createMovieRequest.getProjections().isEmpty()) {
            throw new IllegalArgumentException("Missing movie data");
        }
    }

    private void validateDraft2Fields(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse) {
        if (createMovieRequest.getWriters().isEmpty()
                || createMovieRequest.getCast().isEmpty()
                || createMovieRequest.getPhotoUrls().isEmpty()
                || createMovieRequest.getCoverPhotoUrl() == null) {
            throw new IllegalArgumentException("Missing movie data");
        }
    }

    private void validateDraft1Fields(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse) {
        if (createMovieRequest.getTitle() == null
                || createMovieRequest.getLanguage() == null
                || createMovieRequest.getStartDate() == null
                || createMovieRequest.getEndDate() == null
                || createMovieRequest.getDirector() == null
                || createMovieRequest.getPgRating() == null
                || createMovieRequest.getDuration() == null
                || createMovieRequest.getGenreIds().isEmpty()
                || createMovieRequest.getTrailer() == null
                || createMovieRequest.getSynopsis() == null) {
            throw new IllegalArgumentException("Missing movie data");
        }
    }
}
