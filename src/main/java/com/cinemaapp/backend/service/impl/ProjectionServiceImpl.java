package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.ProjectionRepository;
import com.cinemaapp.backend.service.ProjectionService;
import com.cinemaapp.backend.service.domain.model.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Projection findById(UUID id) {
        return projectionRepository.findById(id);
    }
}
