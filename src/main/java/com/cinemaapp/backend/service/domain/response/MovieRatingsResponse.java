package com.cinemaapp.backend.service.domain.response;

import jakarta.validation.constraints.NotEmpty;

public class MovieRatingsResponse {

    @NotEmpty
    private String imdbRating;

    @NotEmpty
    private String rottenTomatoesRating;

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }

    public void setRottenTomatoesRating(String rottenTomatoesRating) {
        this.rottenTomatoesRating = rottenTomatoesRating;
    }
}
