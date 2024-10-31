package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Venue;

import java.util.List;

public interface VenueRepository {

    List<Venue> findAllVenues();
}
