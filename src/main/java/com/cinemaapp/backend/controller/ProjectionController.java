package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.ProjectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
