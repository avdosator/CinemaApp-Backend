package com.cinemaapp.backend.service.domain.request;

import java.util.UUID;

public class SearchVenuesRequest {

    private int page = 0;
    private int size = 100;
    private UUID city;

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

    public UUID getCity() {
        return city;
    }

    public void setCity(UUID city) {
        this.city = city;
    }
}
