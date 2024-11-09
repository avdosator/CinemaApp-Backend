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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository) {
        this.crudMovieRepository = crudMovieRepository;
    }

    @Override
    public Page<Movie> findMovies(SearchMoviesRequest searchMoviesRequest) {
        Specification<MovieEntity> specification = Specification
                .where(MovieSpecification.hasCurrentlyShowingProjections(searchMoviesRequest.getStartDate(), searchMoviesRequest.getEndDate()))
                .and(MovieSpecification.hasTitleContaining(searchMoviesRequest.getTitle()))
                .and(MovieSpecification.hasProjectionInCity(searchMoviesRequest.getCity()))
                .and(MovieSpecification.hasProjectionInVenue(searchMoviesRequest.getVenue()))
                .and(MovieSpecification.hasGenre(searchMoviesRequest.getGenre()))
                .and(MovieSpecification.hasProjectionWithTime(searchMoviesRequest.getTime()))
                .and(MovieSpecification.hasProjectionOnDate(searchMoviesRequest.getDate()));
        org.springframework.data.domain.Page<MovieEntity> movieEntities = crudMovieRepository.findAll(
                specification, PageRequest.of(searchMoviesRequest.getPage(), searchMoviesRequest.getSize())
        );
        return PageConverter.convertToPage(movieEntities, MovieEntity::toDomainModel);
    }

    @Override
    public Page<Movie> findAllActiveMovies() {
        SearchMoviesRequest searchMoviesRequest = new SearchMoviesRequest();
        searchMoviesRequest.setStartDate(LocalDate.now());
        searchMoviesRequest.setEndDate(LocalDate.now().plusDays(9));
        return this.findMovies(searchMoviesRequest);
    }

    @Override
    public Page<Movie> findAllUpcomingMovies() {
        SearchMoviesRequest searchMoviesRequest = new SearchMoviesRequest();
        searchMoviesRequest.setStartDate(LocalDate.now().plusDays(10));
        return this.findMovies(searchMoviesRequest);
    }
}
