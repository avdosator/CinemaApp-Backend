package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.repository.crud.CrudVenueRepository;
import com.cinemaapp.backend.repository.entity.VenueEntity;
import com.cinemaapp.backend.service.domain.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VenueJpaRepository implements VenueRepository {

    private final CrudVenueRepository crudVenueRepository;

    @Autowired
    public VenueJpaRepository(CrudVenueRepository crudVenueRepository) {
        this.crudVenueRepository = crudVenueRepository;
    }

    @Override
    public List<Venue> findAllVenues() {
        List<VenueEntity> venueEntities = crudVenueRepository.findAll();
        List<Venue> venues = new ArrayList<>();
        for (VenueEntity venueEntity : venueEntities) {
            venues.add(venueEntity.toDomainModel());
        }
        return venues;
    }
}
