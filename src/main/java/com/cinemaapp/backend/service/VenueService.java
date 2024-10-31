package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.Venue;

import java.util.List;

public interface VenueService {

    List<Venue> findAllVenues();
}
