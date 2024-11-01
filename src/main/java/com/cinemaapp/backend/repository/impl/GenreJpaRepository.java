package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.GenreRepository;
import com.cinemaapp.backend.repository.crud.CrudGenreRepository;
import com.cinemaapp.backend.repository.entity.GenreEntity;
import com.cinemaapp.backend.service.domain.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreJpaRepository implements GenreRepository {

    private final CrudGenreRepository crudGenreRepository;

    @Autowired
    public GenreJpaRepository(CrudGenreRepository crudGenreRepository) {
        this.crudGenreRepository = crudGenreRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        List<GenreEntity> genreEntities = crudGenreRepository.findAll();
        List<Genre> genres = new ArrayList<>();
        for (GenreEntity genreEntity : genreEntities) {
            genres.add(genreEntity.toDomainModel());
        }
            return genres;
    }
}
