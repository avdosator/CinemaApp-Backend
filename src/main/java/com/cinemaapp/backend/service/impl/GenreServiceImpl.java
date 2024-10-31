package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.GenreRepository;
import com.cinemaapp.backend.service.GenreService;
import com.cinemaapp.backend.service.domain.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAllGenres();
    }
}
