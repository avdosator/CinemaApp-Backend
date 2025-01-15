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
}
