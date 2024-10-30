package com.cinemaapp.backend.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    private List<T> content = new ArrayList<>();
    private int pageNumber = 0;
    private int pageSize = 0;
    private int totalElements = 0;
    private int totalPages = 0;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }
}