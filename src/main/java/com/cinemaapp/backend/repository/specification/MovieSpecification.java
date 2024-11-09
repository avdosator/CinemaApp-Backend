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

    public static Specification<MovieEntity> hasCurrentlyShowingProjections(LocalDate selectedDate) {
        return (root, query, criteriaBuilder) -> {
            if (selectedDate == null) {
                return null;
            }

            // use distinct if some movie has more than one projection
            if (query != null) {
                query.distinct(true);
            }

            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            return criteriaBuilder.and(
                    criteriaBuilder.lessThan(projections.get("startDate"), LocalDate.now()),
                    criteriaBuilder.greaterThanOrEqualTo(projections.get("startDate"), selectedDate)
            );
        };
    }

    public static Specification<MovieEntity> hasTitleContaining(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
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

    public static Specification<MovieEntity> hasProjectionWithTime(String time) {
        return (root, query, criteriaBuilder) -> {
            if (time == null || time.isEmpty()) {
                return null; // Skip this filter if projectionTime is not provided
            }
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            return criteriaBuilder.like(projections.get("startTimeString"), "%" + time + "%");
        };
    }

    public static Specification<MovieEntity> hasProjectionOnDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return null; // Skip this filter if date is not provided
            }
            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            return criteriaBuilder.equal(projections.get("startDate"), date);
        };
    }
}
