package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class CreateMovieRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String language;

    @NotEmpty
    private String director;

    @NotEmpty
    private String pgRating;

    @NotNull
    @Min(1)
    private Integer duration;

    @NotEmpty
    private List<String> genres;

    @NotEmpty
    private String trailer;

    @NotEmpty
    private String synopsis;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotEmpty
    private List<String> writers;

    @NotEmpty
    private List<String> cast;

    @NotEmpty
    private List<String> photoUrls;

    @NotEmpty
    private List<CreateProjectionRequest> projections;

}
