package com.cinemaapp.backend.config;

import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.repository.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CrudCityRepository crudCityRepository;
    private final CrudGenreRepository crudGenreRepository;
    private final CrudVenueRepository crudVenueRepository;
    private final CrudMovieRepository crudMovieRepository;
    private final CrudUserRepository crudUserRepository;


    @Autowired
    public DatabaseInitializer(CrudCityRepository crudCityRepository, CrudGenreRepository crudGenreRepository,
                               CrudVenueRepository crudVenueRepository, CrudMovieRepository crudMovieRepository,
                               CrudUserRepository crudUserRepository) {
        this.crudCityRepository = crudCityRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudMovieRepository = crudMovieRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (crudCityRepository.findAll().isEmpty() && crudUserRepository.findAll().isEmpty() &&
                crudGenreRepository.findAll().isEmpty() && crudMovieRepository.findAll().isEmpty() &&
                crudVenueRepository.findAll().isEmpty()) {

            // seed city table
            CityEntity sarajevo = new CityEntity();
            CityEntity mostar = new CityEntity();

            sarajevo.setName("Sarajevo");
            sarajevo.setPostalCode(71000);
            sarajevo.setCountry("Bosnia and Herzegovina");
            sarajevo.setCreatedAt(LocalDateTime.now());
            sarajevo.setUpdatedAt(LocalDateTime.now());

            mostar.setName("Mostar");
            mostar.setPostalCode(88000);
            mostar.setCountry("Bosnia and Herzegovina");
            mostar.setCreatedAt(LocalDateTime.now());
            mostar.setUpdatedAt(LocalDateTime.now());

            crudCityRepository.saveAll(Arrays.asList(sarajevo, mostar));

            // seed users table
            UserEntity user = new UserEntity();

            user.setFirstName("Avdo");
            user.setLastName("Sator");
            user.setEmail("avdo.sator@hotmail.com");
            user.setPasswordHash("hashed_password");
            user.setPhone("+38762183628");
            user.setCityEntity(sarajevo);
            user.setRole("ROLE_USER");
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            crudUserRepository.save(user);

            // seed genre table
            List<GenreEntity> genres = new ArrayList<>();

            GenreEntity action = new GenreEntity();
            action.setName("action");
            action.setCreatedAt(LocalDateTime.now());
            action.setUpdatedAt(LocalDateTime.now());
            genres.add(action);

            GenreEntity drama = new GenreEntity();
            drama.setName("drama");
            drama.setCreatedAt(LocalDateTime.now());
            drama.setUpdatedAt(LocalDateTime.now());
            genres.add(drama);

            GenreEntity comedy = new GenreEntity();
            comedy.setName("comedy");
            comedy.setCreatedAt(LocalDateTime.now());
            comedy.setUpdatedAt(LocalDateTime.now());
            genres.add(comedy);

            GenreEntity thriller = new GenreEntity();
            thriller.setName("thriller");
            thriller.setCreatedAt(LocalDateTime.now());
            thriller.setUpdatedAt(LocalDateTime.now());
            genres.add(thriller);

            crudGenreRepository.saveAll(genres);

            // retrieve data due to error
            List<GenreEntity> allGenres = crudGenreRepository.findAll();

            // seed movie table
            String[] statuses = {"active", "draft", "archived"};
            for (int i = 0; i < 20; i++) {
                MovieEntity movie = new MovieEntity();
                movie.setTitle("Sample Movie " + (i + 1));
                movie.setLanguage("English");
                movie.setDirector("Director");
                movie.setPgRating("PG-13");
                movie.setDurationInMinutes(120);
                movie.setWriters(Arrays.asList("Writer 1", "Writer 2"));
                movie.setActors(Arrays.asList("Actor 1", "Actor 2"));
                movie.setImdbRating(7.5 + (i % 2) * 0.5);
                movie.setRottenTomatoesRating(85 + (i % 2) * 5);
                movie.setSynopsis("A sample synopsis for the movie.");
                //movie.setTrailerUrl("http://example.com/trailer" + (i + 1));
                //movie.setCoverPhotoId(UUID.randomUUID());
                movie.setStatus(statuses[i % 2]);
                movie.setCreatedAt(LocalDateTime.now());
                movie.setUpdatedAt(LocalDateTime.now());
                movie.setGenreEntities(List.of(allGenres.get(i % genres.size()))); // Assign a genre

                crudMovieRepository.save(movie);
            }

            
            System.out.println("Database seeded successfully");
        }
    }
}
