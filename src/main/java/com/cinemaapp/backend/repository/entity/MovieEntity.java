package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movie")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MovieEntity {


    // Implement relationship  movie_genre + getters and setters


    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "projection_start_date")
    private Date projectionStart;

    @Column(name = "projection_end_date")
    private Date projectionEnd;

    @Column(name = "director")
    private String director;

    @Column(name = "pg_rating")
    private int pgRating;

    @Column(name = "duration")
    private int durationInMinutes;

    @Column(name = "writers")
    private List<String> writers;

    @Column(name = "actors")
    private List<String> actors;

    @Column(name = "imdb_rating")
    private double imdbRating;

    @Column(name = "rotten_tomatoes_rating")
    private double rottenTomatoesRating;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "cover_photo_id")
    private UUID coverPhotoId;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "movieEntity")
    private List<ProjectionEntity> projectionEntities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
