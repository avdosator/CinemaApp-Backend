package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.VenueService;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@ControllerAdvice
@Tag(name = "Movies", description = "Endpoints for managing movies")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Get all venues", description = "Retrieve a paginated list of venues.")
    @GetMapping
    public Page<Venue> getVenues(@ModelAttribute SearchVenuesRequest searchVenuesRequest) {
        return venueService.findVenues(searchVenuesRequest);
    }
}
