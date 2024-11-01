package com.cinemaapp.backend.service.domain.request;

public class SearchVenuesRequest {

    private int page = 0;
    private int size = 100;

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
}
