package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.Projection;

import java.util.List;
import java.util.UUID;

public interface ProjectionService {

    List<String> findAllProjectionTimes();

    Projection findById(UUID id);
}
