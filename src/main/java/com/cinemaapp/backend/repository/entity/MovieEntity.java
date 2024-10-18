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


    // Implement relationship with projection and movie_genre + getters and setters


    @Id
    @Column(name = "movie_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "projection_start")
    private Date projectionStart;

    @Column(name = "projection_end")
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

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "movie_status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
