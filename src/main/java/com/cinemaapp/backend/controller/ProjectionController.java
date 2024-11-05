package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projections")
public class ProjectionController {

    private final ProjectionService projectionService;

    @Autowired
    public ProjectionController(ProjectionService projectionService) {
        this.projectionService = projectionService;
    }

    @GetMapping("/start-times")
    public List<String> getAllProjectionTimes() {
        return projectionService.findAllProjectionTimes();
    }

}
