package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.Page;
import com.cinemaapp.backend.repository.MovieRepository;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.request.SearchActiveMoviesRequest;
import com.cinemaapp.backend.service.domain.request.SearchUpcomingMoviesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    void test_findActiveMovies_returnsPage() {
        Page<Movie> page = new Page<>();
        ArgumentCaptor<SearchActiveMoviesRequest> requestArgumentCaptor = ArgumentCaptor.forClass(SearchActiveMoviesRequest.class);
        when(movieRepository.findActiveMovies(requestArgumentCaptor.capture()))
                .thenReturn(page);
        SearchActiveMoviesRequest request = new SearchActiveMoviesRequest();
        Page<Movie> moviePage = movieService.findActiveMovies(request);
        assertSame(request, requestArgumentCaptor.getValue());
        assertSame(page, moviePage);
    }

    @Test
    void test_findUpcomingMovies_returnsPage() {
        Page<Movie> page = new Page<>();
        ArgumentCaptor<SearchUpcomingMoviesRequest> requestArgumentCaptor = ArgumentCaptor.forClass(SearchUpcomingMoviesRequest.class);
        when(movieRepository.findUpcomingMovies(requestArgumentCaptor.capture()))
                .thenReturn(page);
        SearchUpcomingMoviesRequest request = new SearchUpcomingMoviesRequest();
        Page<Movie> moviePage = movieService.findUpcomingMovies(request);
        assertSame(request, requestArgumentCaptor.getValue());
        assertSame(page, moviePage);
    }

    @Test
    void findById() {
        UUID movieId = UUID.randomUUID();
        when(movieRepository.findById(movieId)).thenReturn(Movie.builder().id(movieId).build());
        Movie movie = movieService.findById(movieId);
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(movieId, movie.getId());
    }

}