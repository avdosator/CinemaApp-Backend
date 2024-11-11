package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.repository.crud.CrudPhotoRepository;
import com.cinemaapp.backend.repository.crud.CrudVenueRepository;
import com.cinemaapp.backend.repository.entity.PhotoEntity;
import com.cinemaapp.backend.repository.entity.VenueEntity;
import com.cinemaapp.backend.repository.specification.VenueSpecification;
import com.cinemaapp.backend.service.domain.model.Photo;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;
import com.cinemaapp.backend.utils.PageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class VenueJpaRepository implements VenueRepository {

    private final CrudVenueRepository crudVenueRepository;
    private final CrudPhotoRepository crudPhotoRepository;

    @Autowired
    public VenueJpaRepository(CrudVenueRepository crudVenueRepository, CrudPhotoRepository crudPhotoRepository) {
        this.crudVenueRepository = crudVenueRepository;
        this.crudPhotoRepository = crudPhotoRepository;
    }

    @Override
    public Page<Venue> findVenues(SearchVenuesRequest searchVenuesRequest) {
        Specification<VenueEntity> specification = Specification.where(
                VenueSpecification.hasCityName(searchVenuesRequest.getCity())
        );
        org.springframework.data.domain.Page<VenueEntity> venueEntities = crudVenueRepository.findAll(
                specification, PageRequest.of(searchVenuesRequest.getPage(), searchVenuesRequest.getSize())
        );
        return this.setPhotos(PageConverter.convertToPage(venueEntities, VenueEntity::toDomainModel));
    }

    private Page<Venue> setPhotos(Page<Venue> venuePage) {
        for (Venue venue : venuePage.getContent()) {
            PhotoEntity venuePhotoEntity = crudPhotoRepository.findPhotoByRefEntityId(venue.getId());
            Photo photo = venuePhotoEntity.toDomainModel();
            venue.setPhoto(photo);
        }
        return venuePage;
    }
}
