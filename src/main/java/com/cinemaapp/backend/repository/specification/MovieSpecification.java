package com.cinemaapp.backend.repository.specification;

import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.repository.entity.ProjectionEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<MovieEntity> hasActiveProjection() {
        return (root, query, criteriaBuilder) -> {
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            // Check if any projection has status "active"
            return criteriaBuilder.equal(projections.get("status"), "active");
        };
    }

    public static Specification<MovieEntity> hasUpcomingProjection() {
        return (root, query, criteriaBuilder) -> {
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            return criteriaBuilder.equal(projections.get("status"), "upcoming");
        };
    }
}
