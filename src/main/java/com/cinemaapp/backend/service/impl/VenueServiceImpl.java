package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.service.VenueService;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.CreateVenueRequest;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;
import com.cinemaapp.backend.service.domain.request.UpdateVenueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Page<Venue> findVenues(SearchVenuesRequest searchVenuesRequest) {
        return venueRepository.findVenues(searchVenuesRequest);
    }

    @Override
    public Venue createVenue(CreateVenueRequest createVenueRequest) {
        return venueRepository.createVenue(createVenueRequest);
    }

    @Override
    public Venue updateVenue(UUID id, UpdateVenueRequest updateVenueRequest) {
        return venueRepository.updateVenue(id, updateVenueRequest);
    }

    @Override
    public void deleteVenue(UUID id) {
        venueRepository.deleteVenue(id);
    }
}
