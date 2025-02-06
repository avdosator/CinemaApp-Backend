package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.ProjectionEntity;
import com.cinemaapp.backend.repository.entity.ProjectionInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface CrudProjectionInstanceRepository extends JpaRepository<ProjectionInstanceEntity, UUID> {

    @Query("""
                SELECT pi
                FROM ProjectionInstanceEntity pi
                JOIN pi.projectionEntity p
                JOIN p.hallEntity h
                JOIN h.venueEntity v
                JOIN v.cityEntity c
                WHERE c.id = :city
                  AND v.id = :venue
                  AND p.movieEntity.id = :movie
                  AND pi.date = :date
                  AND pi.time = :time
            """)
    Optional<ProjectionInstanceEntity> findProjectionInstance(
            @Param("movie") UUID movie,
            @Param("city") UUID city,
            @Param("venue") UUID venue,
            @Param("date") LocalDate date,
            @Param("time") String time
    );
    void deleteAllByProjectionEntity(ProjectionEntity projectionEntity);
}
