package com.cinemaapp.backend.service.domain.request;

import java.time.LocalDate;
import java.util.UUID;

public class SearchMoviesRequest {

    private int page = 0;
    private int size = 1000;
    private String status = "";
    private LocalDate startDate = null;
    private LocalDate endDate = null;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
