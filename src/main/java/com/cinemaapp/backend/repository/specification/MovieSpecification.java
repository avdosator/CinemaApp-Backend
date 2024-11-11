package com.cinemaapp.backend.repository.specification;

import com.cinemaapp.backend.repository.entity.*;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class MovieSpecification {

    public static Specification<MovieEntity> hasCurrentlyShowingProjectionsUpTo(LocalDate selectedDate) {
        return (root, query, criteriaBuilder) -> {

            // use distinct if some movie has more than one projection
            if (query != null) {
                query.distinct(true);
            }

            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            if (selectedDate == null) {
                return criteriaBuilder.and(
                        criteriaBuilder.lessThan(projections.get("startDate"), LocalDate.now()),
                        criteriaBuilder.greaterThanOrEqualTo(projections.get("endDate"), LocalDate.now())
                );
            }
            return criteriaBuilder.and(
                    criteriaBuilder.lessThan(projections.get("startDate"), LocalDate.now()),
                    criteriaBuilder.greaterThanOrEqualTo(projections.get("startDate"), selectedDate)
            );
        };
    }

    public static Specification<MovieEntity> hasUpcomingProjectionsInRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {

            // use distinct if some movie has more than one projection
            if (query != null) {
                query.distinct(true);
            }

            Join<MovieEntity, ProjectionEntity> projections = root.join("projectionEntities");
            if (startDate == null && endDate == null) {
                return criteriaBuilder.greaterThan(projections.get("startDate"), LocalDate.now());
            }

            // startDate can't be before today because upcoming movies are ones that start tomorrow or later
            if (startDate != null && (startDate.isBefore(LocalDate.now()) && startDate.isEqual(LocalDate.now()))) {
                return criteriaBuilder.and(
                        criteriaBuilder.greaterThan(projections.get("startDate"), LocalDate.now()),
                        criteriaBuilder.between(projections.get("startDate"), LocalDate.now(), endDate)
                );
            }
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThan(projections.get("startDate"), LocalDate.now()),
                    criteriaBuilder.between(projections.get("startDate"), startDate, endDate)
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
}
