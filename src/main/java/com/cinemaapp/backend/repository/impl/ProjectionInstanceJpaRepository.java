package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.ProjectionInstanceRepository;
import com.cinemaapp.backend.repository.crud.CrudProjectionInstanceRepository;
import com.cinemaapp.backend.repository.entity.ProjectionInstanceEntity;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.SearchProjectionInstanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProjectionInstanceJpaRepository implements ProjectionInstanceRepository {

    private final CrudProjectionInstanceRepository crudProjectionInstanceRepository;

    @Autowired
    public ProjectionInstanceJpaRepository(CrudProjectionInstanceRepository crudProjectionInstanceRepository) {
        this.crudProjectionInstanceRepository = crudProjectionInstanceRepository;
    }

    @Override
    public ProjectionInstance findProjectionInstance(SearchProjectionInstanceRequest searchProjectionInstanceRequest) {
        ProjectionInstanceEntity projectionInstanceEntity = crudProjectionInstanceRepository.findProjectionInstance(
                searchProjectionInstanceRequest.getMovie(),
                searchProjectionInstanceRequest.getCity(),
                searchProjectionInstanceRequest.getVenue(),
                searchProjectionInstanceRequest.getDate(),
                searchProjectionInstanceRequest.getTime()
        ).orElseThrow(); // Handle Optional as per your application's needs
        return projectionInstanceEntity.toDomainModel();
    }

    @Override
    public ProjectionInstance findById(UUID id) {
        ProjectionInstanceEntity projectionInstanceEntity = crudProjectionInstanceRepository.findById(id).orElse(null);
        return projectionInstanceEntity == null ? null : projectionInstanceEntity.toDomainModel();
    }
}
