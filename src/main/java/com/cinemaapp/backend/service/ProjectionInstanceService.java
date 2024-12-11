package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;

public interface ProjectionInstanceService {

    ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest);
}
