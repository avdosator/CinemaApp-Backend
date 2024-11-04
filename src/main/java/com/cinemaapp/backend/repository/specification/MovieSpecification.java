package com.cinemaapp.backend.repository.specification;

import com.cinemaapp.backend.repository.entity.*;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

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

    public static Specification<MovieEntity> hasProjectionWithinDateRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            if (startDate == null || endDate == null) {
                return null;
            }
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(projections.get("startDate"), startDate),
                    criteriaBuilder.lessThanOrEqualTo(projections.get("endDate"), endDate)
            );
        };
    }

    public static Specification<MovieEntity> hasGenre(UUID genreId) {
        return (root, query, criteriaBuilder) -> {
            if (genreId == null) {
                return null;
            }
            Join<MovieEntity, GenreEntity> genres = root.join("genreEntities");
            return criteriaBuilder.equal(genres.get("id"), genreId);
        };
    }

    public static Specification<MovieEntity> hasProjectionInCity(UUID cityId) {
        return (root, query, criteriaBuilder) -> {
            if (cityId == null) {
                return null;
            }
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            Join<ProjectionEntity, HallEntity> hall = projections.join("hallEntity");
            Join<HallEntity, VenueEntity> venue = hall.join("venueEntity");
            Join<VenueEntity, CityEntity> city = venue.join("cityEntity");
            return criteriaBuilder.equal(city.get("id"), cityId);
        };
    }

    public static Specification<MovieEntity> hasProjectionInVenue(UUID venueId) {
        return (root, query, criteriaBuilder) -> {
            if (venueId == null) {
                return null;
            }
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            Join<ProjectionEntity, HallEntity> hall = projections.join("hallEntity");
            Join<HallEntity, VenueEntity> venue = hall.join("venueEntity");
            return criteriaBuilder.equal(venue.get("id"), venueId);
        };
    }

    public static Specification<MovieEntity> hasProjectionWithTime(String projectionTime) {
        return (root, query, criteriaBuilder) -> {
            if (projectionTime == null || projectionTime.isEmpty()) {
                return null; // Skip this filter if projectionTime is not provided
            }
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            return criteriaBuilder.isMember(projectionTime, projections.get("startTime"));
        };
    }
}
