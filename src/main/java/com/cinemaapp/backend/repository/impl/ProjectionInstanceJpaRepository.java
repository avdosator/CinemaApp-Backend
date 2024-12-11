package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.ProjectionInstanceRepository;
import com.cinemaapp.backend.repository.crud.CrudProjectionInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectionInstanceJpaRepository implements ProjectionInstanceRepository {

    private final CrudProjectionInstanceRepository crudProjectionInstanceRepository;

    @Autowired
    public ProjectionInstanceJpaRepository(CrudProjectionInstanceRepository crudProjectionInstanceRepository) {
        this.crudProjectionInstanceRepository = crudProjectionInstanceRepository;
    }
}
