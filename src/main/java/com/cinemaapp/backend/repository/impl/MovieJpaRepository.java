package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.repository.crud.*;
import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.repository.specification.MovieSpecification;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.Photo;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
import com.cinemaapp.backend.service.domain.request.CreateProjectionRequest;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import com.cinemaapp.backend.service.domain.response.MovieRatingsResponse;
import com.cinemaapp.backend.utils.PageConverter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;
    private final CrudPhotoRepository crudPhotoRepository;
    private final CrudGenreRepository crudGenreRepository;
    private final CrudVenueRepository crudVenueRepository;
    private final CrudProjectionRepository crudProjectionRepository;
    private final CrudProjectionInstanceRepository crudProjectionInstanceRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository, CrudPhotoRepository crudPhotoRepository,
                              CrudGenreRepository crudGenreRepository, CrudVenueRepository crudVenueRepository,
                              CrudProjectionRepository crudProjectionRepository,
                              CrudProjectionInstanceRepository crudProjectionInstanceRepository) {
        this.crudMovieRepository = crudMovieRepository;
        this.crudPhotoRepository = crudPhotoRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudProjectionRepository = crudProjectionRepository;
        this.crudProjectionInstanceRepository = crudProjectionInstanceRepository;
    }

    @Override
    public Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest) {
        Specification<MovieEntity> specification = Specification
                .where(MovieSpecification.hasCurrentlyShowingProjectionsUpTo(searchActiveMoviesRequest.getDate()))
                .and(MovieSpecification.hasTitleContaining(searchActiveMoviesRequest.getTitle()))
                .and(MovieSpecification.hasProjectionInCity(searchActiveMoviesRequest.getCity()))
                .and(MovieSpecification.hasProjectionInVenue(searchActiveMoviesRequest.getVenue()))
                .and(MovieSpecification.hasGenre(searchActiveMoviesRequest.getGenre()))
                .and(MovieSpecification.hasProjectionWithTime(searchActiveMoviesRequest.getTime())
                        .and(MovieSpecification.isReleasedMovie()));

        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(
                specification, PageRequest.of(searchActiveMoviesRequest.getPage(),
                        searchActiveMoviesRequest.getSize(),
                        Sort.by(Sort.Direction.ASC, "title")
                )
        );

        Page<Movie> movies = PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
        movies = mapPhotosToMovies(movies);
        return movies;
    }

    @Override
    public Page<Movie> findUpcomingMovies(SearchUpcomingMoviesRequest searchUpcomingMoviesRequest) {
        Specification<MovieEntity> specification = Specification
                .where(MovieSpecification.hasUpcomingProjectionsInRange(
                        searchUpcomingMoviesRequest.getStartDate(),
                        searchUpcomingMoviesRequest.getEndDate()))
                .and(MovieSpecification.hasTitleContaining(searchUpcomingMoviesRequest.getTitle()))
                .and(MovieSpecification.hasProjectionInCity(searchUpcomingMoviesRequest.getCity()))
                .and(MovieSpecification.hasProjectionInVenue(searchUpcomingMoviesRequest.getVenue()))
                .and(MovieSpecification.hasGenre(searchUpcomingMoviesRequest.getGenre())
                        .and(MovieSpecification.isReleasedMovie()));

        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(
                specification, PageRequest.of(searchUpcomingMoviesRequest.getPage(),
                        searchUpcomingMoviesRequest.getSize(),
                        Sort.by(Sort.Direction.ASC, "title")
                )
        );

        Page<Movie> movies = PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
        movies = mapPhotosToMovies(movies);
        return movies;
    }

    @Override
    public Movie findById(UUID id) {
        MovieEntity movieEntity = crudMovieRepository.findById(id).orElseThrow();
        Movie movie = movieEntity.toDomainModel();
        List<PhotoEntity> photoEntities = crudPhotoRepository.findByRefEntityId(movieEntity.getId());
        List<Photo> moviePhotos = new ArrayList<>();
        for (PhotoEntity photoEntity : photoEntities) {
            moviePhotos.add(photoEntity.toDomainModel());
        }
        movie.setPhotos(moviePhotos);
        return movie;
    }

    @Override
    public Movie createMovie(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        if (createMovieRequest.getMovieId() != null) {
            return applyChanges(createMovieRequest, movieRatingsResponse, status);
        } else {
            return createNewMovie(createMovieRequest, movieRatingsResponse, status);
        }
    }

    private Movie createNewMovie(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        MovieEntity movieEntity = new MovieEntity();
        if (status.equals("draft-1")) {
            return setDraft1Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        } else if (status.equals("draft-2")) {
            return setDraft2Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        } else {
            return setAllFields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        }
    }

    private Movie applyChanges(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        MovieEntity movieEntity = crudMovieRepository.findById(createMovieRequest.getMovieId()).orElseThrow();

        if (status.equals("draft-1")) {
            return setDraft1Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        } else if (status.equals("draft-2")) {
            return setDraft2Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        } else {
            return setAllFields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        }
    }

    private Movie setAllFields(MovieEntity movieEntity, CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        setDraft1Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        setDraft2Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);

        // Delete Projection placeholder
        ProjectionEntity placeholderProjection = crudProjectionRepository.findByMovieEntityAndStatus(movieEntity, "placeholder");
        crudProjectionRepository.delete(placeholderProjection);

        // Create projections
        List<ProjectionEntity> projectionEntities = createProjectionEntities(createMovieRequest, movieEntity);
        movieEntity.setProjectionEntities(projectionEntities);

        return crudMovieRepository.save(movieEntity).toDomainModel();
    }

    private Movie setDraft2Fields(MovieEntity movieEntity, CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        setDraft1Fields(movieEntity, createMovieRequest, movieRatingsResponse, status);
        movieEntity.setActors(createMovieRequest.getCast().toArray(String[]::new));
        movieEntity.setWriters(createMovieRequest.getWriters().toArray(String[]::new));
        movieEntity = crudMovieRepository.save(movieEntity);

        // Process photos and get the cover photo ID along with saved photos
        Pair<UUID, List<PhotoEntity>> photoProcessingResult = processPhotosAndFindCoverPhoto(movieEntity.getId(), createMovieRequest);
        List<PhotoEntity> savedPhotoEntities = photoProcessingResult.getRight();
        movieEntity.setCoverPhotoId(photoProcessingResult.getLeft());
        List<Photo> photos = savedPhotoEntities.stream()
                .map(PhotoEntity::toDomainModel)
                .toList();
        Movie movie = movieEntity.toDomainModel();
        movie.setPhotos(photos);

        return movie;
    }

    private Movie setDraft1Fields(MovieEntity movieEntity, CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse, String status) {
        movieEntity.setTitle(createMovieRequest.getTitle());
        movieEntity.setLanguage(createMovieRequest.getLanguage());
        movieEntity.setDirector(createMovieRequest.getDirector());
        movieEntity.setPgRating(createMovieRequest.getPgRating());
        movieEntity.setDurationInMinutes(createMovieRequest.getDuration());
        movieEntity.setTrailerUrl(createMovieRequest.getTrailer());
        movieEntity.setSynopsis(createMovieRequest.getSynopsis());
        movieEntity.setGenreEntities(crudGenreRepository.findAllById(createMovieRequest.getGenreIds()));
        movieEntity.setImdbRating(movieRatingsResponse.getImdbRating());
        movieEntity.setRottenTomatoesRating(movieRatingsResponse.getRottenTomatoesRating());
        movieEntity.setStatus(status);

        // Create projection placeholder
        updateProjectionDate(movieEntity, createMovieRequest, status);
        return crudMovieRepository.save(movieEntity).toDomainModel();
    }

    private void updateProjectionDate(MovieEntity movieEntity, CreateMovieRequest createMovieRequest, String status) {
        List<ProjectionEntity> projectionEntities = crudProjectionRepository.findByMovieEntity(movieEntity);
        if (projectionEntities.isEmpty()) {
            createProjectionPlaceholder(movieEntity, createMovieRequest);
        } else {
            updateProjectionAndInstances(movieEntity, projectionEntities.get(0), createMovieRequest, status);
        }
    }

    // Possible option - don't allow projection date range change
    private void assertValidDates(MovieEntity movieEntity, CreateMovieRequest createMovieRequest) {
        if (sameDates(movieEntity.getProjectionEntities().get(0), createMovieRequest)) {
            throw new IllegalArgumentException("Date change at this step is not supported");
        }
    }

    private boolean sameDates(ProjectionEntity projectionEntity, CreateMovieRequest createMovieRequest) {
        if (projectionEntity.getStartDate() != createMovieRequest.getStartDate() ||
                projectionEntity.getEndDate() != createMovieRequest.getEndDate()) {
            return false;
        }
        return true;
    }

    private void updateProjectionAndInstances(MovieEntity movieEntity, ProjectionEntity projectionEntity, CreateMovieRequest createMovieRequest, String status) {
        if ("draft-3".equals(status)) {
            crudProjectionRepository.deleteAllByMovieEntity(movieEntity);
            createProjectionEntities(createMovieRequest, movieEntity);
        } else if (!sameDates(projectionEntity, createMovieRequest)) {
            if (!"draft-3".equals(movieEntity.getStatus())) {
                updatePlaceholderDate(movieEntity, createMovieRequest);
            } else {
                crudProjectionRepository.deleteAllByMovieEntity(movieEntity);
                createProjectionEntities(createMovieRequest, movieEntity);
            }
        }
    }

    private void updatePlaceholderDate(MovieEntity movieEntity, CreateMovieRequest createMovieRequest) {
        ProjectionEntity projectionEntity = crudProjectionRepository.findAnyByMovieEntity(movieEntity);
        projectionEntity.setStartDate(createMovieRequest.getStartDate());
        projectionEntity.setEndDate(createMovieRequest.getEndDate());
        crudProjectionRepository.save(projectionEntity);
    }

    private void createProjectionPlaceholder(MovieEntity movieEntity, CreateMovieRequest createMovieRequest) {
        ProjectionEntity draftProjection = new ProjectionEntity();
        draftProjection.setMovieEntity(movieEntity);
        draftProjection.setStartDate(createMovieRequest.getStartDate());
        draftProjection.setEndDate(createMovieRequest.getEndDate());
        draftProjection.setStatus("placeholder");
        draftProjection.setCreatedAt(LocalDateTime.now());
        draftProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(draftProjection);
    }

    private Pair<UUID, List<PhotoEntity>> processPhotosAndFindCoverPhoto(UUID movieId, CreateMovieRequest createMovieRequest) {
        // Fetch existing photos (if any)
        List<PhotoEntity> existingPhotos = crudPhotoRepository.findByRefEntityId(movieId);

        // Get new photo URLs from request
        List<String> newPhotoUrls = createMovieRequest.getPhotoUrls();

        List<PhotoEntity> updatedPhotoEntities = new ArrayList<>();

        if (existingPhotos.isEmpty()) {
            // No existing photos → Create new ones
            updatedPhotoEntities = createPhotoEntities(movieId, createMovieRequest);
        } else {
            // Update existing photos if there were some changes
            for (int i = 0; i < existingPhotos.size(); i++) {
                PhotoEntity existingPhoto = existingPhotos.get(i);
                String newPhotoUrl = newPhotoUrls.get(i);

                if (!existingPhoto.getUrl().equals(newPhotoUrl)) {
                    // URL has changed → Update the existing entity
                    existingPhoto.setUrl(newPhotoUrl);
                    existingPhoto.setUpdatedAt(LocalDateTime.now());
                }

                updatedPhotoEntities.add(existingPhoto);
            }

            // Save only updated photos
            updatedPhotoEntities = crudPhotoRepository.saveAll(updatedPhotoEntities);
        }

        // Find the cover photo ID
        UUID coverPhotoId = getCoverPhotoId(createMovieRequest.getCoverPhotoUrl(), updatedPhotoEntities);

        return Pair.of(coverPhotoId, updatedPhotoEntities);
    }

    private List<PhotoEntity> createPhotoEntities(UUID movieId, CreateMovieRequest createMovieRequest) {
        List<PhotoEntity> photoEntities = new ArrayList<>();
        for (String photoUrl : createMovieRequest.getPhotoUrls()) {
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setEntityType("movie");
            photoEntity.setUrl(photoUrl);
            photoEntity.setRefEntityId(movieId);
            photoEntity.setCreatedAt(LocalDateTime.now());
            photoEntity.setUpdatedAt(LocalDateTime.now());
            photoEntities.add(photoEntity);
        }
        return crudPhotoRepository.saveAll(photoEntities);
    }

    private UUID getCoverPhotoId(String coverPhotoUrl, List<PhotoEntity> photoEntities) {
        return photoEntities.stream()
                .filter(photoEntity -> photoEntity.getUrl().equals(coverPhotoUrl))
                .map(PhotoEntity::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cover photo URL not found in saved photo entities."));
    }

    private List<ProjectionEntity> createProjectionEntities(CreateMovieRequest createMovieRequest, MovieEntity movieEntity) {
        // Group the projection requests by venueId
        Map<UUID, List<CreateProjectionRequest>> groupedProjections = createMovieRequest.getProjections()
                .stream()
                .collect(Collectors.groupingBy(CreateProjectionRequest::getVenueId));

        List<ProjectionEntity> projectionEntities = new ArrayList<>();

        for (Map.Entry<UUID, List<CreateProjectionRequest>> entry : groupedProjections.entrySet()) {
            UUID venueId = entry.getKey();
            List<CreateProjectionRequest> projectionRequests = entry.getValue();

            // Find the venue entity
            VenueEntity venueEntity = crudVenueRepository.findById(venueId)
                    .orElseThrow(() -> new NoSuchElementException("Venue not found: " + venueId));

            // Extract all start times for this venue
            List<String> startTimes = projectionRequests.stream()
                    .map(CreateProjectionRequest::getProjectionTime)
                    .toList();

            // Create the ProjectionEntity
            ProjectionEntity projectionEntity = new ProjectionEntity();
            projectionEntity.setHallEntity(venueEntity.getHallEntities().get(0)); // Assuming the first hall is used
            projectionEntity.setMovieEntity(movieEntity);
            projectionEntity.setStatus("upcoming");
            projectionEntity.setStartDate(createMovieRequest.getStartDate());
            projectionEntity.setEndDate(createMovieRequest.getEndDate());
            projectionEntity.setStartTime(startTimes.toArray(new String[0])); // Convert to array
            projectionEntity.setCreatedAt(LocalDateTime.now());
            projectionEntity.setUpdatedAt(LocalDateTime.now());
            ProjectionEntity savedProjectionEntity = crudProjectionRepository.save(projectionEntity);
            projectionEntities.add(savedProjectionEntity);

            createProjectionInstances(projectionRequests, savedProjectionEntity);
        }

        return projectionEntities;
    }

    private void createProjectionInstances(List<CreateProjectionRequest> projectionRequests, ProjectionEntity projectionEntity) {
        List<ProjectionInstanceEntity> projectionInstanceEntities = new ArrayList<>();

        for (CreateProjectionRequest request : projectionRequests) {
            // Loop through all dates in the projectionEntity's date range
            LocalDate currentDate = projectionEntity.getStartDate();
            while (!currentDate.isAfter(projectionEntity.getEndDate())) {
                // Create a ProjectionInstanceEntity for the current date and time
                ProjectionInstanceEntity instance = new ProjectionInstanceEntity();
                instance.setProjectionEntity(projectionEntity);
                instance.setDate(currentDate);
                instance.setTime(request.getProjectionTime());
                instance.setCreatedAt(LocalDateTime.now());
                instance.setUpdatedAt(LocalDateTime.now());

                projectionInstanceEntities.add(instance);

                currentDate = currentDate.plusDays(1);
            }
        }

        // Save all instances to the database
        crudProjectionInstanceRepository.saveAll(projectionInstanceEntities);
    }

    public Page<Movie> mapPhotosToMovies(Page<Movie> movies) {

        // Collect Movie IDs from the page content
        List<UUID> movieIds = movies.getContent().stream()
                .map(Movie::getId)
                .toList();

        List<PhotoEntity> allPhotos = crudPhotoRepository.findAllByRefEntityIdIn(movieIds);

        // Convert PhotoEntity to Photo and group them by refEntityId in a Map
        Map<UUID, List<Photo>> photosByMovieId = allPhotos.stream()
                .map(PhotoEntity::toDomainModel)
                .collect(Collectors.groupingBy(Photo::getRefEntityId));

        // Assign photos to each movie by fetching from the map
        for (Movie movie : movies.getContent()) {
            List<Photo> moviePhotos = photosByMovieId.getOrDefault(movie.getId(), new ArrayList<>());
            movie.setPhotos(moviePhotos);
        }
        return movies;
    }
}
