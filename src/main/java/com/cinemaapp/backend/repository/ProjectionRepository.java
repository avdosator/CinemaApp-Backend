package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Projection;

import java.util.List;
import java.util.UUID;

public interface ProjectionRepository {

    List<String> findAllProjectionTimes();

    Projection findById(UUID id);
}
