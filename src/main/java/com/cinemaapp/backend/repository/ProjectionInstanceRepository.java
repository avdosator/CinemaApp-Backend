package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;

import java.util.UUID;

public interface ProjectionInstanceRepository {

    ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest);
    ProjectionInstance findById(UUID id);
}
