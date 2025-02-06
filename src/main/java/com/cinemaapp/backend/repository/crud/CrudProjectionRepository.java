package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.repository.entity.ProjectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CrudProjectionRepository extends JpaRepository<ProjectionEntity, UUID> {
    ProjectionEntity findByMovieEntityAndStatus(MovieEntity movieEntity, String placeholder);

    ProjectionEntity findAnyByMovieEntity(MovieEntity movieEntity);

    void deleteAllByMovieEntity(MovieEntity movieEntity);

    List<ProjectionEntity> findByMovieEntity(MovieEntity movieEntity);
}
