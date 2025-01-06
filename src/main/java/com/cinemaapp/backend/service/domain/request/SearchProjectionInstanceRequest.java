package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class SearchProjectionInstanceRequest {

    @NotNull
    private UUID movie;

    @NotNull
    private UUID city;

    @NotNull
    private UUID venue;

    @NotNull
    private LocalDate date;

    @NotNull
    private String time;

    public @NotNull UUID getMovie() {
        return movie;
    }

    public void setMovie(UUID movie) {
        this.movie = movie;
    }

    public UUID getCity() {
        return city;
    }

    public void setCity(UUID city) {
        this.city = city;
    }

    public UUID getVenue() {
        return venue;
    }

    public void setVenue(UUID venue) {
        this.venue = venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
