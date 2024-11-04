package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.ProjectionRepository;
import com.cinemaapp.backend.repository.crud.CrudProjectionRepository;
import com.cinemaapp.backend.repository.entity.ProjectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProjectionJpaRepository implements ProjectionRepository {

    private final CrudProjectionRepository crudProjectionRepository;

    @Autowired
    public ProjectionJpaRepository(CrudProjectionRepository crudProjectionRepository) {
        this.crudProjectionRepository = crudProjectionRepository;
    }

    @Override
    public List<String> findAllProjectionTimes() {
        List<ProjectionEntity> projectionEntities = crudProjectionRepository.findAll();
        Set<String> projectionTimes = new HashSet<>();
        for (ProjectionEntity projectionEntity : projectionEntities) {
            for(String projectionTime : projectionEntity.getStartTime()) {
                projectionTimes.add(projectionTime);
            }
        }
        return projectionTimes.stream().sorted().toList();
    }
}
