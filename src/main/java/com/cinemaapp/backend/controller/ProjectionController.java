package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.ProjectionService;
import com.cinemaapp.backend.service.domain.model.Projection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projections")
@Tag(name = "Projections", description = "Endpoints for managing projections")
public class ProjectionController {

    private final ProjectionService projectionService;

    @Autowired
    public ProjectionController(ProjectionService projectionService) {
        this.projectionService = projectionService;
    }

    @Operation(summary = "Get all distinct projection times", description = "Retrieve a list of possible projection start times.")
    @GetMapping("/start-times")
    public List<String> getAllProjectionTimes() {
        return projectionService.findAllProjectionTimes();
    }

    @Operation(summary = "Get projection by ID", description = "Retrieve a projection by its unique identifier.")
    @GetMapping("/{id}")
    public Projection getProjectionById(
            @Parameter(description = "Unique identifier of the projection")
            @PathVariable UUID id) {
        return projectionService.findById(id);
    }
}
