package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.GenreService;
import com.cinemaapp.backend.service.domain.model.Genre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Tag(name = "Genres", description = "Endpoints for managing genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(summary = "Get all genres", description = "Retrieve a list of all movie genres.")
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }
}
