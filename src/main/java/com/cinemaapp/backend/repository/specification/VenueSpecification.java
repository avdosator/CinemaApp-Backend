package com.cinemaapp.backend.repository.specification;

import com.cinemaapp.backend.repository.entity.CityEntity;
import com.cinemaapp.backend.repository.entity.VenueEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class VenueSpecification {

    // filter venues by city name
    public static Specification<VenueEntity> hasCityName(UUID cityId) {
        return (root, query, criteriaBuilder) -> {
            if (cityId == null) {
                return null;
            }
            Join<VenueEntity, CityEntity> city = root.join("cityEntity");
            return criteriaBuilder.equal(city.get("id"), cityId);
        };
    }
}
