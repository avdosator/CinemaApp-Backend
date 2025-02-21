package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.Genre;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.Projection;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movie")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MovieEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "director")
    private String director;

    @Column(name = "pg_rating")
    private String pgRating;

    @Column(name = "duration")
    private int durationInMinutes;

    @Column(name = "writers")
    private String[] writers;

    @Column(name = "actors")
    private String[] actors;

    @Column(name = "imdb_rating")
    private String imdbRating;

    @Column(name = "rotten_tomatoes_rating")
    private String rottenTomatoesRating;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "cover_photo_id")
    private UUID coverPhotoId;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genreEntities;

    @OneToMany(mappedBy = "movieEntity", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<ProjectionEntity> projectionEntities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public UUID getCoverPhotoId() {
        return coverPhotoId;
    }

    public void setCoverPhotoId(UUID coverPhotoId) {
        this.coverPhotoId = coverPhotoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GenreEntity> getGenreEntities() {
        return genreEntities;
    }

    public void setGenreEntities(List<GenreEntity> genreEntities) {
        this.genreEntities = genreEntities;
    }

    public List<ProjectionEntity> getProjectionEntities() {
        return projectionEntities;
    }

    public void setProjectionEntities(List<ProjectionEntity> projectionEntities) {
        this.projectionEntities = projectionEntities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Movie toDomainModel() {
        List<Genre> genres = (this.genreEntities == null ? Collections.emptyList() : this.genreEntities.stream()
                .map(GenreEntity::toDomainModel)
                .toList());
        List<Projection> projections = (this.projectionEntities == null ?
                Collections.emptyList() : this.projectionEntities.stream()
                .map(ProjectionEntity::toDomainModel)
                .toList());

        return Movie.builder()
                .id(this.id)
                .title(this.title)
                .language(this.language)
                .director(this.director)
                .pgRating(this.pgRating)
                .durationInMinutes(this.durationInMinutes)
                .writers(this.writers)
                .actors(this.actors)
                .imdbRating(this.imdbRating)
                .rottenTomatoesRating(this.rottenTomatoesRating)
                .synopsis(this.synopsis)
                .trailerUrl(this.trailerUrl)
                .coverPhotoId(this.coverPhotoId)
                .status(this.status)
                .genres(genres)
                .projections(projections)
                //.photos(photos)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
