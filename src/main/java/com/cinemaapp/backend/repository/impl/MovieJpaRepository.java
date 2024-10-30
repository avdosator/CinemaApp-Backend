package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.repository.crud.CrudMovieRepository;
import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.repository.specification.MovieSpecification;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;
import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.utils.PageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository) {
        this.crudMovieRepository = crudMovieRepository;
    }

    @Override
    public Page<Movie> findAllMovies(SearchMoviesRequest searchMoviesRequest) {
        Pageable pageable = PageRequest.of(searchMoviesRequest.getPage(), searchMoviesRequest.getSize());
        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(pageable);
        return PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
    }

    @Override
    public Page<Movie> findAllActiveMovies(SearchMoviesRequest searchMoviesRequest) {
        Specification<MovieEntity> specification = MovieSpecification.hasActiveProjection();
        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(
                specification, PageRequest.of(searchMoviesRequest.getPage(), searchMoviesRequest.getSize())
        );
        return PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
    }

    @Override
    public Page<Movie> findAllUpcomingMovies(SearchMoviesRequest searchMoviesRequest) {
        Specification<MovieEntity> specification = MovieSpecification.hasUpcomingProjection();
        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(
                specification, PageRequest.of(searchMoviesRequest.getPage(), searchMoviesRequest.getSize())
        );

        return PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
    }
}
