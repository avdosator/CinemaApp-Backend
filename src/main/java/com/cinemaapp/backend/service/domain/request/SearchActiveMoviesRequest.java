package com.cinemaapp.backend.service.domain.request;

import java.time.LocalDate;
import java.util.UUID;

public class SearchActiveMoviesRequest {

    private int page = 0;
    private int size = 1000;
    private LocalDate date = null;
    private String title = "";
    private UUID city = null;
    private UUID venue = null;
    private UUID genre = null;
    private String time = "";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UUID getGenre() {
        return genre;
    }

    public void setGenre(UUID genre) {
        this.genre = genre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
