package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Movie {

    private final UUID id;
    private final String title;
    private final String language;
    private final String director;
    private final String pgRating;
    private final int durationInMinutes;
    private final String[] writers;
    private final String[] actors;
    private final double imdbRating;
    private final double rottenTomatoesRating;
    private final String synopsis;
    private final String trailerUrl;
    private final UUID coverPhotoId;
    private final String status;
    private final List<Genre> genres;
    private final List<Projection> projections;
    private List<Photo> photos;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    Movie(UUID id, String title, String language, String director, String pgRating, int durationInMinutes,
          String[] writers, String[] actors, double imdbRating, double rottenTomatoesRating, String synopsis,
          String trailerUrl, UUID coverPhotoId, String status, List<Genre> genres, List<Projection> projections,
          List<Photo> photos, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        this.photos = photos;
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

    public String[] getWriters() {
        return writers;
    }

    public String[] getActors() {
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

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public static class MovieBuilder {
        private UUID id;
        private String title;
        private String language;
        private String director;
        private String pgRating;
        private int durationInMinutes;
        private String[] writers;
        private String[] actors;
        private double imdbRating;
        private double rottenTomatoesRating;
        private String synopsis;
        private String trailerUrl;
        private UUID coverPhotoId;
        private String status;
        private List<Genre> genres;
        private List<Projection> projections;
        private List<Photo> photos;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        MovieBuilder() {
        }

        public MovieBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MovieBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder language(String language) {
            this.language = language;
            return this;
        }

        public MovieBuilder director(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder pgRating(String pgRating) {
            this.pgRating = pgRating;
            return this;
        }

        public MovieBuilder durationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

        public MovieBuilder writers(String[] writers) {
            this.writers = writers;
            return this;
        }

        public MovieBuilder actors(String[] actors) {
            this.actors = actors;
            return this;
        }

        public MovieBuilder imdbRating(double imdbRating) {
            this.imdbRating = imdbRating;
            return this;
        }

        public MovieBuilder rottenTomatoesRating(double rottenTomatoesRating) {
            this.rottenTomatoesRating = rottenTomatoesRating;
            return this;
        }

        public MovieBuilder synopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public MovieBuilder trailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
            return this;
        }

        public MovieBuilder coverPhotoId(UUID coverPhotoId) {
            this.coverPhotoId = coverPhotoId;
            return this;
        }

        public MovieBuilder status(String status) {
            this.status = status;
            return this;
        }

        public MovieBuilder genres(List<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public MovieBuilder projections(List<Projection> projections) {
            this.projections = projections;
            return this;
        }

        public MovieBuilder photos(List<Photo> photos) {
            this.photos = photos;
            return this;
        }

        public MovieBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MovieBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Movie build() {
            return new Movie(
                    this.id,
                    this.title,
                    this.language,
                    this.director,
                    this.pgRating,
                    this.durationInMinutes,
                    this.writers,
                    this.actors,
                    this.imdbRating,
                    this.rottenTomatoesRating,
                    this.synopsis,
                    this.trailerUrl,
                    this.coverPhotoId,
                    this.status,
                    this.genres,
                    this.projections,
                    this.photos,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
