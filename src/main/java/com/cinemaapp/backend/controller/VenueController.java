package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.service.VenueService;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.CreateVenueRequest;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;
import com.cinemaapp.backend.service.domain.request.UpdateVenueRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/venues")
@ControllerAdvice
@Tag(name = "Venues", description = "Endpoints for managing venues")
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

    @Operation(summary = "Create venue", description = "Create new venue.")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Venue createVenue(
            @Parameter(description = "Data needed for movie creation")
            @RequestBody @Valid CreateVenueRequest createVenueRequest) {
        return venueService.createVenue(createVenueRequest);
    }

    @Operation(summary = "Update venue", description = "Update a venue by its unique identifier.")
    @PatchMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public Venue getMovieById(
            @Parameter(description = "Unique identifier of the venue")
            @PathVariable UUID id,
            @RequestBody UpdateVenueRequest updateVenueRequest) {
        return venueService.updateVenue(id, updateVenueRequest);
    }
}
