package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.UUID;

public interface CrudGenreRepository extends JpaRepository<GenreEntity, UUID> {

    GenreEntity findByName(String name);

    List<GenreEntity> findByNameIn(List<String> names);
}
