package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.VenueRepository;
import com.cinemaapp.backend.repository.crud.*;
import com.cinemaapp.backend.repository.entity.HallEntity;
import com.cinemaapp.backend.repository.entity.PhotoEntity;
import com.cinemaapp.backend.repository.entity.SeatEntity;
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
import java.util.ArrayList;
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
    private final CrudSeatRepository crudSeatRepository;

    @Autowired
    public VenueJpaRepository(CrudVenueRepository crudVenueRepository, CrudPhotoRepository crudPhotoRepository, CrudCityRepository crudCityRepository, CrudHallRepository crudHallRepository, CrudSeatRepository crudSeatRepository) {
        this.crudVenueRepository = crudVenueRepository;
        this.crudPhotoRepository = crudPhotoRepository;
        this.crudCityRepository = crudCityRepository;
        this.crudHallRepository = crudHallRepository;
        this.crudSeatRepository = crudSeatRepository;
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

        VenueEntity savedVenueEntity = crudVenueRepository.save(venueEntity);
        Photo photo = createPhoto(createVenueRequest.getPhotoUrl(), savedVenueEntity.getId());
        Venue venue = savedVenueEntity.toDomainModel();
        venue.setPhoto(photo);

        return venue;
    }

    private Photo createPhoto(String photoUrl, UUID venueId) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setEntityType("venue");
        photoEntity.setUrl(photoUrl);
        photoEntity.setRefEntityId(venueId);
        photoEntity.setCreatedAt(LocalDateTime.now());
        photoEntity.setUpdatedAt(LocalDateTime.now());

        return crudPhotoRepository.save(photoEntity).toDomainModel();
    }

    private List<HallEntity> createHall() {
        HallEntity hallEntity = new HallEntity();
        hallEntity.setName("Hall 1");
        hallEntity.setSeatEntities(createSeats());
        hallEntity.setCreatedAt(LocalDateTime.now());
        hallEntity.setUpdatedAt(LocalDateTime.now());
        return List.of(crudHallRepository.save(hallEntity));
    }

    private List<SeatEntity> createSeats() {
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String[] seatTypes = {"regular", "regular", "regular", "regular", "regular", "regular", "VIP", "VIP", "love"};
        List<SeatEntity> seatEntities = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            String seatType = seatTypes[i];
            int seatCount = (i == 8) ? 4 : 8; // Row "I" (index 8) should have only 4 seats.

            for (int j = 1; j <= seatCount; j++) {
                SeatEntity seatEntity = new SeatEntity();
                seatEntity.setType(seatType);
                seatEntity.setCreatedAt(LocalDateTime.now());
                seatEntity.setNumber(row + j);
                seatEntities.add(seatEntity);
            }
        }
        return crudSeatRepository.saveAll(seatEntities);
    }
}
