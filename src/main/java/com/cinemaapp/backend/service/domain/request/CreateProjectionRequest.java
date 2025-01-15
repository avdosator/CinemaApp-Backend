package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateProjectionRequest {

    @NotEmpty
    private String projectionTime;

    @NotNull
    private UUID cityId;

    @NotNull
    private UUID venueId;

    public String getProjectionTime() {
        return projectionTime;
    }

    public void setProjectionTime(String projectionTime) {
        this.projectionTime = projectionTime;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public void setVenueId(UUID venueId) {
        this.venueId = venueId;
    }
}
