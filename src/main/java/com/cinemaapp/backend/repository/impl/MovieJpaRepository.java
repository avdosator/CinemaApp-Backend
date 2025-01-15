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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

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

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository, CrudPhotoRepository crudPhotoRepository,
                              CrudGenreRepository crudGenreRepository, CrudVenueRepository crudVenueRepository,
                              CrudProjectionRepository crudProjectionRepository) {
        this.crudMovieRepository = crudMovieRepository;
        this.crudPhotoRepository = crudPhotoRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudProjectionRepository = crudProjectionRepository;
    }

    @Override
    public Page<Movie> findActiveMovies(SearchActiveMoviesRequest searchActiveMoviesRequest) {
        Specification<MovieEntity> specification = Specification
                .where(MovieSpecification.hasCurrentlyShowingProjectionsUpTo(searchActiveMoviesRequest.getDate()))
                .and(MovieSpecification.hasTitleContaining(searchActiveMoviesRequest.getTitle()))
                .and(MovieSpecification.hasProjectionInCity(searchActiveMoviesRequest.getCity()))
                .and(MovieSpecification.hasProjectionInVenue(searchActiveMoviesRequest.getVenue()))
                .and(MovieSpecification.hasGenre(searchActiveMoviesRequest.getGenre()))
                .and(MovieSpecification.hasProjectionWithTime(searchActiveMoviesRequest.getTime()));

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
                .and(MovieSpecification.hasGenre(searchUpcomingMoviesRequest.getGenre()));

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
    public Movie createMovie(CreateMovieRequest createMovieRequest, MovieRatingsResponse movieRatingsResponse) {
        List<GenreEntity> genreEntities = crudGenreRepository.findByNameIn(createMovieRequest.getGenres());

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(createMovieRequest.getTitle());
        movieEntity.setLanguage(createMovieRequest.getLanguage());
        movieEntity.setDirector(createMovieRequest.getDirector());
        movieEntity.setPgRating(createMovieRequest.getPgRating());
        movieEntity.setDurationInMinutes(createMovieRequest.getDuration());
        movieEntity.setWriters(createMovieRequest.getWriters());
        movieEntity.setActors(createMovieRequest.getCast());
        movieEntity.setSynopsis(createMovieRequest.getSynopsis());
        movieEntity.setTrailerUrl(createMovieRequest.getTrailer());
        movieEntity.setStatus("active");
        movieEntity.setGenreEntities(genreEntities);
        /*Set Cover photo id*/
        movieEntity.setImdbRating(movieRatingsResponse.getImdbRating());
        movieEntity.setRottenTomatoesRating(movieRatingsResponse.getRottenTomatoesRating());
        movieEntity.setCreatedAt(LocalDateTime.now());
        movieEntity.setUpdatedAt(LocalDateTime.now());
        MovieEntity savedMovieEntity = crudMovieRepository.save(movieEntity);
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
            projectionEntities.add(projectionEntity);
        }

        return crudProjectionRepository.saveAll(projectionEntities);
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
