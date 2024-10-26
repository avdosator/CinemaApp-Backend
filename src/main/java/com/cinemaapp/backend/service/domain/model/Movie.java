package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Movie {

    private UUID id;
    private String title;
    private String language;
    private String director;
    private String pgRating;
    private int durationInMinutes;
    private List<String> writers;
    private List<String> actors;
    private double imdbRating;
    private double rottenTomatoesRating;
    private String synopsis;
    private String trailerUrl;
    private UUID coverPhotoId;
    private String status;
    private Set<Genre> genres;
    private List<Projection> projections;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Movie(UUID id, String title, String language, String director, String pgRating, int durationInMinutes, List<String> writers, List<String> actors, double imdbRating, double rottenTomatoesRating, String synopsis, String trailerUrl, UUID coverPhotoId, String status, Set<Genre> genres, List<Projection> projections, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.director = director;
        this.pgRating = pgRating;
        this.durationInMinutes = durationInMinutes;
        this.writers = writers;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.rottenTomatoesRating = rottenTomatoesRating;
        this.synopsis = synopsis;
        this.trailerUrl = trailerUrl;
        this.coverPhotoId = coverPhotoId;
        this.status = status;
        this.genres = genres;
        this.projections = projections;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getLanguage() {
        return language;
    }
    public String getDirector() {
        return director;
    }
    public String getPgRating() {
        return pgRating;
    }
    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    public List<String> getWriters() {
        return writers;
    }
    public List<String> getActors() {
        return actors;
    }
    public double getImdbRating() {
        return imdbRating;
    }
    public double getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public String getTrailerUrl() {
        return trailerUrl;
    }
    public UUID getCoverPhotoId() {
        return coverPhotoId;
    }
    public String getStatus() {
        return status;
    }
    public Set<Genre> getGenres() {
        return genres;
    }
    public List<Projection> getProjections() {
        return projections;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    
}
