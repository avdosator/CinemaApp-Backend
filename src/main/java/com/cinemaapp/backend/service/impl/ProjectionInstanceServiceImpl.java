package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.ProjectionInstanceRepository;
import com.cinemaapp.backend.service.ProjectionInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectionInstanceServiceImpl implements ProjectionInstanceService {

    private final ProjectionInstanceRepository projectionInstanceRepository;

    @Autowired
    public ProjectionInstanceServiceImpl(ProjectionInstanceRepository projectionInstanceRepository) {
        this.projectionInstanceRepository = projectionInstanceRepository;
    }
}
