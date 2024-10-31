package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAllGenres();
}
