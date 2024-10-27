package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Genre {

    private final UUID id;
    private final String name;
    private final List<Movie> movies;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Genre(UUID id, String name, List<Movie> movies, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.movies = movies;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static GenreBuilder builder() {
        return new GenreBuilder();
    }

    public static class GenreBuilder {
        private UUID id;
        private String name;
        private List<Movie> movies;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        GenreBuilder() {}

        public GenreBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public GenreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GenreBuilder movies(List<Movie> movies) {
            this.movies = movies;
            return this;
        }

        public GenreBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public GenreBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Genre build() {
            return new Genre(
                    this.id,
                    this.name,
                    this.movies,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
