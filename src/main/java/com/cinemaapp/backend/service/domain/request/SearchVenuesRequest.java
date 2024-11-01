package com.cinemaapp.backend.service.domain.request;

public class SearchVenuesRequest {

    private int page = 0;
    private int size = 100;
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
