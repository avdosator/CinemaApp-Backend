package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.repository.crud.CrudMovieRepository;
import com.cinemaapp.backend.repository.entity.MovieEntity;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchMoviesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieJpaRepository implements MovieRepository {

    private final CrudMovieRepository crudMovieRepository;

    @Autowired
    public MovieJpaRepository(CrudMovieRepository crudMovieRepository) {
        this.crudMovieRepository = crudMovieRepository;
    }

    @Override
    public List<Movie> findAllMovies(SearchMoviesRequest searchMoviesRequest) {
        Pageable pageable = PageRequest.of(searchMoviesRequest.getPage(), searchMoviesRequest.getSize());
        Page<MovieEntity> movieEntities = crudMovieRepository.findAll(pageable);
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(movieEntity.toDomainModel());
        }
        return movies;
    }
}
