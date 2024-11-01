package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAllGenres();
}
