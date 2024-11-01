package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CrudMovieRepository extends JpaRepository<MovieEntity, UUID>, JpaSpecificationExecutor<MovieEntity> {

    MovieEntity findByTitle(String title);
}
