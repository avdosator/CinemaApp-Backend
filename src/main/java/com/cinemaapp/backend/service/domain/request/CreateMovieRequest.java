package com.cinemaapp.backend.service.domain.request;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateMovieRequest {

    private UUID movieId;

    private String title;

    private String language;

    private String director;

    private String pgRating;

    private Integer duration;

    private List<UUID> genreIds;

    private String trailer;

    private String synopsis;

    private LocalDate startDate;

    private LocalDate endDate;

    private String[] writers;

    private String[] cast;

    private List<String> photoUrls;

    private List<CreateProjectionRequest> projections;

    private String coverPhotoUrl;

    public UUID getMovieId() {
        return movieId;
    }

    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPgRating() {
        return pgRating;
    }

    public void setPgRating(String pgRating) {
        this.pgRating = pgRating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<UUID> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<UUID> genreIds) {
        this.genreIds = genreIds;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<CreateProjectionRequest> getProjections() {
        return projections;
    }

    public void setProjections(List<CreateProjectionRequest> projections) {
        this.projections = projections;
    }

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }
}
