package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.response.MovieRatingsResponse;

public interface MovieRatingService {

    MovieRatingsResponse getMovieRatings(String title);
}
