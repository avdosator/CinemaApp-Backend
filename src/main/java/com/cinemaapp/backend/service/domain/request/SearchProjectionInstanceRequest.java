package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class SearchProjectionInstanceRequest {

    @NotNull
    private UUID city;

    @NotNull
    private UUID venue;

    @NotNull
    private LocalDate date;

    @NotNull
    private String time;

    public @NotNull UUID getCity() {
        return city;
    }

    public void setCity(@NotNull UUID city) {
        this.city = city;
    }

    public @NotNull UUID getVenue() {
        return venue;
    }

    public void setVenue(@NotNull UUID venue) {
        this.venue = venue;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public @NotNull String getTime() {
        return time;
    }

    public void setTime(@NotNull String time) {
        this.time = time;
    }
}
