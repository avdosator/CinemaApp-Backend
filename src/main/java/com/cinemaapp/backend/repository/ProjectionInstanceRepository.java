package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;

public interface ProjectionInstanceRepository {

    ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest);
}
