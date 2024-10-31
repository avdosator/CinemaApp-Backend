package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.service.VenueService;
import com.cinemaapp.backend.service.domain.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }
    @Override
    public List<Venue> findAllVenues() {
        return venueRepository.findAllVenues();
    }
}
