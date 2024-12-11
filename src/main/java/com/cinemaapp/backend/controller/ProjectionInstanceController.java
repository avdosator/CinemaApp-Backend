package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.ProjectionInstanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projection/instances")
@Tag(name = "Projection instances", description = "Endpoints for managing projection instances")
public class ProjectionInstanceController {

    private final ProjectionInstanceService projectionInstanceService;

    @Autowired
    public ProjectionInstanceController(ProjectionInstanceService projectionInstanceService) {
        this.projectionInstanceService = projectionInstanceService;
    }
}
