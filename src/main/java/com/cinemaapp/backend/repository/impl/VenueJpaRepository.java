package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.repository.crud.CrudCityRepository;
import com.cinemaapp.backend.repository.crud.CrudHallRepository;
import com.cinemaapp.backend.repository.crud.CrudPhotoRepository;
import com.cinemaapp.backend.repository.crud.CrudVenueRepository;
import com.cinemaapp.backend.repository.entity.HallEntity;
import com.cinemaapp.backend.repository.entity.PhotoEntity;
import com.cinemaapp.backend.repository.entity.VenueEntity;
import com.cinemaapp.backend.repository.specification.VenueSpecification;
import com.cinemaapp.backend.service.domain.model.Photo;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.cinemaapp.backend.service.domain.request.CreateVenueRequest;
import com.cinemaapp.backend.service.domain.request.SearchVenuesRequest;
import com.cinemaapp.backend.utils.PageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class VenueJpaRepository implements VenueRepository {

    private final CrudVenueRepository crudVenueRepository;
    private final CrudPhotoRepository crudPhotoRepository;
    private final CrudCityRepository crudCityRepository;
    private final CrudHallRepository crudHallRepository;

    @Autowired
    public VenueJpaRepository(CrudVenueRepository crudVenueRepository, CrudPhotoRepository crudPhotoRepository, CrudCityRepository crudCityRepository, CrudHallRepository crudHallRepository) {
        this.crudVenueRepository = crudVenueRepository;
        this.crudPhotoRepository = crudPhotoRepository;
        this.crudCityRepository = crudCityRepository;
        this.crudHallRepository = crudHallRepository;
    }

    @Override
    public Page<Venue> findVenues(SearchVenuesRequest searchVenuesRequest) {
        Specification<VenueEntity> specification = Specification.where(
                VenueSpecification.hasCityName(searchVenuesRequest.getCity())
        );
        org.springframework.data.domain.Page<VenueEntity> venueEntities = crudVenueRepository.findAll(
                specification, PageRequest.of(searchVenuesRequest.getPage(), searchVenuesRequest.getSize())
        );
        Page<Venue> venuePage = PageConverter.convertToPage(venueEntities, VenueEntity::toDomainModel);

        // Collect Venue IDs from the page content
        List<UUID> venueIds = venuePage.getContent().stream()
                .map(Venue::getId)
                .collect(Collectors.toList());

        List<PhotoEntity> venuePhotos = crudPhotoRepository.findAllByRefEntityIdIn(venueIds);

        // Map each Venue ID to its corresponding Photo
        Map<UUID, Photo> photosByVenueId = venuePhotos.stream()
                .collect(Collectors.toMap(PhotoEntity::getRefEntityId, PhotoEntity::toDomainModel));

        //  Assign the photo to each venue in the page
        for (Venue venue : venuePage.getContent()) {
            venue.setPhoto(photosByVenueId.get(venue.getId()));
        }
        return venuePage;
    }

    @Override
    public Venue createVenue(CreateVenueRequest createVenueRequest) {
        VenueEntity venueEntity = new VenueEntity();
        venueEntity.setName(createVenueRequest.getName());
        venueEntity.setPhone(createVenueRequest.getPhone());
        venueEntity.setStreet(createVenueRequest.getStreet());
        venueEntity.setStreetNumber(createVenueRequest.getStreetNumber());
        venueEntity.setCityEntity(crudCityRepository.findById(createVenueRequest.getCityId()).orElseThrow());
        venueEntity.setHallEntities(createHall());
        venueEntity.setCreatedAt(LocalDateTime.now());
        venueEntity.setUpdatedAt(LocalDateTime.now());
    }

   
}
