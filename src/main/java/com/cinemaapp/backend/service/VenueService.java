package com.cinemaapp.backend.service;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.CreateVenueRequest;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;

public interface VenueService {

    Page<Venue> findVenues(SearchVenuesRequest searchVenuesRequest);
    Venue createVenue(CreateVenueRequest createVenueRequest);
}
