package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.ProjectionInstanceRepository;
import com.cinemaapp.backend.service.ProjectionInstanceService;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectionInstanceServiceImpl implements ProjectionInstanceService {

    private final ProjectionInstanceRepository projectionInstanceRepository;

    @Autowired
    public ProjectionInstanceServiceImpl(ProjectionInstanceRepository projectionInstanceRepository) {
        this.projectionInstanceRepository = projectionInstanceRepository;
    }

    @Override
    public ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest) {
        return projectionInstanceRepository.findProjectionInstance(searchProjectionInstanceRequest);
    }

    @Override
    public ProjectionInstance findById(UUID id) {
        return projectionInstanceRepository.findById(id);
    }
}
