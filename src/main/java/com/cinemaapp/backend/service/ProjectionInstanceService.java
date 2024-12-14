package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;

import java.util.UUID;

public interface ProjectionInstanceService {

    ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest);
    ProjectionInstance findById(UUID id);
}
