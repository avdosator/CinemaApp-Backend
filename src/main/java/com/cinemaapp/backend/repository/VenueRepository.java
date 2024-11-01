package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;

public interface VenueRepository {

    Page<Venue> findAllVenues(SearchVenuesRequest searchVenuesRequest);
    Page<Venue> findVenuesByCityName(String cityName);
}
