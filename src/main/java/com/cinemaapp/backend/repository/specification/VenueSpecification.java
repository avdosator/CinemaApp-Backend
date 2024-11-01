package com.cinemaapp.backend.repository.specification;

import com.cinemaapp.backend.repository.entity.CityEntity;
import com.cinemaapp.backend.repository.entity.VenueEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class VenueSpecification {

    // filter venues by city name
    public static Specification<VenueEntity> hasCityName(String cityName) {
        return (root, query, criteriaBuilder) -> {
            if (cityName == null || cityName.isEmpty()) {
                return null;
            }
            Join<VenueEntity, CityEntity> city = root.join("cityEntity");
            return criteriaBuilder.equal(city.get("name"), cityName);
        };
    }
}
