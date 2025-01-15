package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.repository.crud.CrudGenreRepository;
import com.cinemaapp.backend.repository.crud.CrudMovieRepository;
import com.cinemaapp.backend.repository.crud.CrudPhotoRepository;
import com.cinemaapp.backend.repository.entity.GenreEntity;
import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.repository.entity.PhotoEntity;
import com.cinemaapp.backend.repository.specification.MovieSpecification;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.Photo;
import com.cinemaapp.backend.service.domain.request.CreateMovieRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;
    private final CrudPhotoRepository crudPhotoRepository;
    private final CrudGenreRepository crudGenreRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository, CrudPhotoRepository crudPhotoRepository,
                              CrudGenreRepository crudGenreRepository) {
        this.crudMovieRepository = crudMovieRepository;
        this.crudPhotoRepository = crudPhotoRepository;
        this.crudGenreRepository = crudGenreRepository;
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
