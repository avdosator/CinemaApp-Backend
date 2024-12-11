package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.ProjectionInstanceService;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projection/instance")
@Tag(name = "Projection instances", description = "Endpoints for managing projection instances")
public class ProjectionInstanceController {

    private final ProjectionInstanceService projectionInstanceService;

    @Autowired
    public ProjectionInstanceController(ProjectionInstanceService projectionInstanceService) {
        this.projectionInstanceService = projectionInstanceService;
    }

    @GetMapping()
    public ProjectionInstance getProjectionInstance(@ModelAttribute @Valid SearchProjectionInstanceRequest searchProjectionInstanceRequest) {
        return projectionInstanceService.findProjectionInstance(searchProjectionInstanceRequest);
    }
}
