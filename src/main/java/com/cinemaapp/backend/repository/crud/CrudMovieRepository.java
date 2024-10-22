package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudMovieRepository extends JpaRepository<MovieEntity, UUID> {
}
