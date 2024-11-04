package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.ProjectionRepository;
import com.cinemaapp.backend.service.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionServiceImpl implements ProjectionService {

    private final ProjectionRepository projectionRepository;

    @Autowired
    public ProjectionServiceImpl(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public List<String> findAllProjectionTimes() {
        return projectionRepository.findAllProjectionTimes();
    }
}
