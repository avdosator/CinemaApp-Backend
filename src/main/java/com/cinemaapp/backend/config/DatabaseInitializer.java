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
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CrudCityRepository crudCityRepository;
    private final CrudGenreRepository crudGenreRepository;
    private final CrudVenueRepository crudVenueRepository;
    private final CrudMovieRepository crudMovieRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudProjectionRepository crudProjectionRepository;


    @Autowired
    public DatabaseInitializer(CrudCityRepository crudCityRepository, CrudGenreRepository crudGenreRepository,
                               CrudVenueRepository crudVenueRepository, CrudMovieRepository crudMovieRepository,
                               CrudUserRepository crudUserRepository, CrudProjectionRepository crudProjectionRepository) {
        this.crudCityRepository = crudCityRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudMovieRepository = crudMovieRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudProjectionRepository = crudProjectionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        if (crudCityRepository.findAll().isEmpty() && crudUserRepository.findAll().isEmpty() &&
//                crudGenreRepository.findAll().isEmpty() && crudMovieRepository.findAll().isEmpty() &&
//                crudVenueRepository.findAll().isEmpty()) {

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
        action.setName("Action");
        action.setCreatedAt(LocalDateTime.now());
        action.setUpdatedAt(LocalDateTime.now());
        genres.add(action);

        GenreEntity drama = new GenreEntity();
        drama.setName("Drama");
        drama.setCreatedAt(LocalDateTime.now());
        drama.setUpdatedAt(LocalDateTime.now());
        genres.add(drama);

        GenreEntity comedy = new GenreEntity();
        comedy.setName("Comedy");
        comedy.setCreatedAt(LocalDateTime.now());
        comedy.setUpdatedAt(LocalDateTime.now());
        genres.add(comedy);

        GenreEntity thriller = new GenreEntity();
        thriller.setName("Thriller");
        thriller.setCreatedAt(LocalDateTime.now());
        thriller.setUpdatedAt(LocalDateTime.now());
        genres.add(thriller);

        GenreEntity adventure = new GenreEntity();
        adventure.setName("Adventure");
        adventure.setCreatedAt(LocalDateTime.now());
        adventure.setUpdatedAt(LocalDateTime.now());
        genres.add(adventure);

        GenreEntity war = new GenreEntity();
        war.setName("War");
        war.setCreatedAt(LocalDateTime.now());
        war.setUpdatedAt(LocalDateTime.now());
        genres.add(war);

        GenreEntity sciFi = new GenreEntity();
        sciFi.setName("Sci-Fi");
        adventure.setCreatedAt(LocalDateTime.now());
        adventure.setUpdatedAt(LocalDateTime.now());
        genres.add(sciFi);

        crudGenreRepository.saveAll(genres);

        // List<GenreEntity> allGenres = crudGenreRepository.findAll();

        // seed movie table
        String[] statuses = {"active", "draft", "archived"};
        /*
            MovieEntity movie1 = new MovieEntity();
            movie1.setTitle("Avatar");
            movie1.setLanguage("English");
            //movie.setDirector("Director");
            movie1.setPgRating("PG-13");
            movie1.setDurationInMinutes(120);
            //movie.setWriters(Arrays.asList("Writer 1", "Writer 2"));
            //movie.setActors(Arrays.asList("Actor 1", "Actor 2"));
            //movie.setImdbRating(7.5 + (i % 2) * 0.5);
            //movie.setRottenTomatoesRating(85 + (i % 2) * 5);
            movie1.setSynopsis("A sample synopsis for the movie.");
            movie1.setTrailerUrl("http://example.com/trailer");
            //movie.setCoverPhotoId(UUID.randomUUID());
            movie1.setStatus("active");
            movie1.setCreatedAt(LocalDateTime.now());
            movie1.setUpdatedAt(LocalDateTime.now());
            movie1.setGenreEntities(List.of(crudGenreRepository.findByName("Action")));

            crudMovieRepository.save(movie1);
        */

        MovieEntity movie1 = new MovieEntity();
        movie1.setTitle("Avatar");
        movie1.setLanguage("English");
        movie1.setPgRating("PG-13");
        movie1.setDurationInMinutes(162);
        movie1.setSynopsis("A paraplegic Marine dispatched to the moon Pandora becomes torn between following his orders and protecting the world he feels is his home.");
        movie1.setTrailerUrl("https://www.youtube.com/watch?v=d9MyW72ELq0");
        movie1.setStatus("active");
        movie1.setCreatedAt(LocalDateTime.now());
        movie1.setUpdatedAt(LocalDateTime.now());
        movie1.setGenreEntities(List.of(crudGenreRepository.findByName("Adventure")));
        crudMovieRepository.save(movie1);

        MovieEntity movie2 = new MovieEntity();
        movie2.setTitle("Inception");
        movie2.setLanguage("English");
        movie2.setPgRating("PG-13");
        movie2.setDurationInMinutes(148);
        movie2.setSynopsis("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.");
        movie2.setTrailerUrl("https://www.youtube.com/watch?v=YoHD9XEInc0");
        movie2.setStatus("active");
        movie2.setCreatedAt(LocalDateTime.now());
        movie2.setUpdatedAt(LocalDateTime.now());
        movie2.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie2);

        MovieEntity movie3 = new MovieEntity();
        movie3.setTitle("Titanic");
        movie3.setLanguage("English");
        movie3.setPgRating("PG-13");
        movie3.setDurationInMinutes(195);
        movie3.setSynopsis("A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.");
        movie3.setTrailerUrl("https://www.youtube.com/watch?v=kVrqfYjkTdQ");
        movie3.setStatus("active");
        movie3.setCreatedAt(LocalDateTime.now());
        movie3.setUpdatedAt(LocalDateTime.now());
        movie3.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie3);

        MovieEntity movie4 = new MovieEntity();
        movie4.setTitle("The Dark Knight");
        movie4.setLanguage("English");
        movie4.setPgRating("PG-13");
        movie4.setDurationInMinutes(152);
        movie4.setSynopsis("When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.");
        movie4.setTrailerUrl("https://www.youtube.com/watch?v=EXeTwQWrcwY");
        movie4.setStatus("active");
        movie4.setCreatedAt(LocalDateTime.now());
        movie4.setUpdatedAt(LocalDateTime.now());
        movie4.setGenreEntities(List.of(crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie4);

        MovieEntity movie5 = new MovieEntity();
        movie5.setTitle("The Matrix");
        movie5.setLanguage("English");
        movie5.setPgRating("R");
        movie5.setDurationInMinutes(136);
        movie5.setSynopsis("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
        movie5.setTrailerUrl("https://www.youtube.com/watch?v=vKQi3bBA1y8");
        movie5.setStatus("active");
        movie5.setCreatedAt(LocalDateTime.now());
        movie5.setUpdatedAt(LocalDateTime.now());
        movie5.setGenreEntities(List.of(crudGenreRepository.findByName("Comedy"), crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie5);

        MovieEntity movie6 = new MovieEntity();
        movie6.setTitle("Interstellar");
        movie6.setLanguage("English");
        movie6.setPgRating("PG-13");
        movie6.setDurationInMinutes(169);
        movie6.setSynopsis("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.");
        movie6.setTrailerUrl("https://www.youtube.com/watch?v=2LqzF5WauAw");
        movie6.setStatus("active");
        movie6.setCreatedAt(LocalDateTime.now());
        movie6.setUpdatedAt(LocalDateTime.now());
        movie6.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie6);

        MovieEntity movie7 = new MovieEntity();
        movie7.setTitle("Gladiator");
        movie7.setLanguage("English");
        movie7.setPgRating("R");
        movie7.setDurationInMinutes(155);
        movie7.setSynopsis("A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.");
        movie7.setTrailerUrl("https://www.youtube.com/watch?v=P5ieIbInFpg");
        movie7.setStatus("active");
        movie7.setCreatedAt(LocalDateTime.now());
        movie7.setUpdatedAt(LocalDateTime.now());
        movie7.setGenreEntities(List.of(crudGenreRepository.findByName("Action"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie7);

        MovieEntity movie8 = new MovieEntity();
        movie8.setTitle("The Godfather");
        movie8.setLanguage("English");
        movie8.setPgRating("R");
        movie8.setDurationInMinutes(175);
        movie8.setSynopsis("The aging patriarch of an organized crime dynasty transfers control of his empire to his reluctant son.");
        movie8.setTrailerUrl("https://www.youtube.com/watch?v=UaVTIH8mujA");
        movie8.setStatus("active");
        movie8.setCreatedAt(LocalDateTime.now());
        movie8.setUpdatedAt(LocalDateTime.now());
        movie8.setGenreEntities(List.of(crudGenreRepository.findByName("Action"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie8);

        MovieEntity movie9 = new MovieEntity();
        movie9.setTitle("Pulp Fiction");
        movie9.setLanguage("English");
        movie9.setPgRating("R");
        movie9.setDurationInMinutes(154);
        movie9.setSynopsis("The lives of two mob hitmen, a boxer, a gangster, and his wife intertwine in four tales of violence and redemption.");
        movie9.setTrailerUrl("https://www.youtube.com/watch?v=tGpTpVyI_OQ");
        movie9.setStatus("active");
        movie9.setCreatedAt(LocalDateTime.now());
        movie9.setUpdatedAt(LocalDateTime.now());
        movie9.setGenreEntities(List.of(crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie9);

        MovieEntity movie10 = new MovieEntity();
        movie10.setTitle("Schindler's List");
        movie10.setLanguage("English");
        movie10.setPgRating("R");
        movie10.setDurationInMinutes(195);
        movie10.setSynopsis("In German-occupied Poland during World War II, industrialist Oskar Schindler saves the lives of more than a thousand Jews by employing them in his factories.");
        movie10.setTrailerUrl("https://www.youtube.com/watch?v=mxphAlJID9U");
        movie10.setStatus("active");
        movie10.setCreatedAt(LocalDateTime.now());
        movie10.setUpdatedAt(LocalDateTime.now());
        movie10.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie10);

        MovieEntity movie11 = new MovieEntity();
        movie11.setTitle("Fight Club");
        movie11.setLanguage("English");
        movie11.setPgRating("R");
        movie11.setDurationInMinutes(139);
        movie11.setSynopsis("An insomniac office worker and a soapmaker form an underground fight club that evolves into something much more.");
        movie11.setTrailerUrl("https://www.youtube.com/watch?v=BdJKm16Co6M");
        movie11.setStatus("active");
        movie11.setCreatedAt(LocalDateTime.now());
        movie11.setUpdatedAt(LocalDateTime.now());
        movie11.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie11);

        MovieEntity movie12 = new MovieEntity();
        movie12.setTitle("Forrest Gump");
        movie12.setLanguage("English");
        movie12.setPgRating("PG-13");
        movie12.setDurationInMinutes(142);
        movie12.setSynopsis("The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of a man with a low IQ.");
        movie12.setTrailerUrl("https://www.youtube.com/watch?v=bLvqoHBptj");
        movie12.setStatus("active");
        movie12.setCreatedAt(LocalDateTime.now());
        movie12.setUpdatedAt(LocalDateTime.now());
        movie12.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie12);

        MovieEntity movie13 = new MovieEntity();
        movie13.setTitle("The Lion King");
        movie13.setLanguage("English");
        movie13.setPgRating("G");
        movie13.setDurationInMinutes(88);
        movie13.setSynopsis("Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.");
        movie13.setTrailerUrl("https://www.youtube.com/watch?v=o17MF9vnabg");
        movie13.setStatus("active");
        movie13.setCreatedAt(LocalDateTime.now());
        movie13.setUpdatedAt(LocalDateTime.now());
        movie13.setGenreEntities(List.of(crudGenreRepository.findByName("Adventure")));
        crudMovieRepository.save(movie13);

        MovieEntity movie14 = new MovieEntity();
        movie14.setTitle("Saving Private Ryan");
        movie14.setLanguage("English");
        movie14.setPgRating("R");
        movie14.setDurationInMinutes(169);
        movie14.setSynopsis("Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.");
        movie14.setTrailerUrl("https://www.youtube.com/watch?v=9CiW_DgxCnQ");
        movie14.setStatus("active");
        movie14.setCreatedAt(LocalDateTime.now());
        movie14.setUpdatedAt(LocalDateTime.now());
        movie14.setGenreEntities(List.of(crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie14);

        MovieEntity movie15 = new MovieEntity();
        movie15.setTitle("The Shawshank Redemption");
        movie15.setLanguage("English");
        movie15.setPgRating("R");
        movie15.setDurationInMinutes(142);
        movie15.setSynopsis("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");
        movie15.setTrailerUrl("https://www.youtube.com/watch?v=PLl99DlL6b4");
        movie15.setStatus("active");
        movie15.setCreatedAt(LocalDateTime.now());
        movie15.setUpdatedAt(LocalDateTime.now());
        movie15.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie15);

        MovieEntity movie16 = new MovieEntity();
        movie16.setTitle("The Green Mile");
        movie16.setLanguage("English");
        movie16.setPgRating("R");
        movie16.setDurationInMinutes(189);
        movie16.setSynopsis("The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.");
        movie16.setTrailerUrl("https://www.youtube.com/watch?v=Ki4haFrqSrw");
        movie16.setStatus("active");
        movie16.setCreatedAt(LocalDateTime.now());
        movie16.setUpdatedAt(LocalDateTime.now());
        movie16.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie16);

        MovieEntity movie17 = new MovieEntity();
        movie17.setTitle("The Avengers");
        movie17.setLanguage("English");
        movie17.setPgRating("PG-13");
        movie17.setDurationInMinutes(143);
        movie17.setSynopsis("Earth's mightiest heroes must come together and learn to fight as a team to stop the mischievous Loki and his alien army from enslaving humanity.");
        movie17.setTrailerUrl("https://www.youtube.com/watch?v=eOrNdBpGMv8s");
        movie17.setStatus("active");
        movie17.setCreatedAt(LocalDateTime.now());
        movie17.setUpdatedAt(LocalDateTime.now());
        movie17.setGenreEntities(List.of(crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie17);

        MovieEntity movie18 = new MovieEntity();
        movie18.setTitle("Jurassic Park");
        movie18.setLanguage("English");
        movie18.setPgRating("PG-13");
        movie18.setDurationInMinutes(127);
        movie18.setSynopsis("A pragmatic paleontologist visiting an almost complete theme park is tasked with protecting a couple of kids after a power failure causes the park's cloned dinosaurs to run loose.");
        movie18.setTrailerUrl("https://www.youtube.com/watch?v=RFinNxS5KN4");
        movie18.setStatus("active");
        movie18.setCreatedAt(LocalDateTime.now());
        movie18.setUpdatedAt(LocalDateTime.now());
        movie18.setGenreEntities(List.of(crudGenreRepository.findByName("Adventure"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie18);

        MovieEntity movie19 = new MovieEntity();
        movie19.setTitle("The Silence of the Lambs");
        movie19.setLanguage("English");
        movie19.setPgRating("R");
        movie19.setDurationInMinutes(118);
        movie19.setSynopsis("A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to catch another serial killer, a madman who skins his victims.");
        movie19.setTrailerUrl("https://www.youtube.com/watch?v=W6Mm8Sbe__o");
        movie19.setStatus("active");
        movie19.setCreatedAt(LocalDateTime.now());
        movie19.setUpdatedAt(LocalDateTime.now());
        movie19.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie19);

        MovieEntity movie20 = new MovieEntity();
        movie20.setTitle("The Lord of the Rings: The Fellowship of the Ring");
        movie20.setLanguage("English");
        movie20.setPgRating("PG-13");
        movie20.setDurationInMinutes(178);
        movie20.setSynopsis("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.");
        movie20.setTrailerUrl("https://www.youtube.com/watch?v=r5X-hFf6Bwo");
        movie20.setStatus("active");
        movie20.setCreatedAt(LocalDateTime.now());
        movie20.setUpdatedAt(LocalDateTime.now());
        movie20.setGenreEntities(List.of(crudGenreRepository.findByName("Adventure"), crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie20);

        MovieEntity movie21 = new MovieEntity();
        movie21.setTitle("No Man's Land");
        movie21.setLanguage("Bosnian");
        movie21.setPgRating("R");
        movie21.setDurationInMinutes(98);
        movie21.setSynopsis("Bosnian and Serbian soldiers find themselves trapped in no man's land during the Bosnian War.");
        movie21.setTrailerUrl("https://example.com/nomansland");
        movie21.setStatus("active");
        movie21.setCreatedAt(LocalDateTime.now());
        movie21.setUpdatedAt(LocalDateTime.now());
        movie21.setGenreEntities(List.of(crudGenreRepository.findByName("War"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie21);

        MovieEntity movie22 = new MovieEntity();
        movie22.setTitle("Grbavica");
        movie22.setLanguage("Bosnian");
        movie22.setPgRating("PG-13");
        movie22.setDurationInMinutes(107);
        movie22.setSynopsis("A single mother struggles to raise her daughter in post-war Sarajevo while hiding a painful secret.");
        movie22.setTrailerUrl("https://example.com/grbavica");
        movie22.setStatus("active");
        movie22.setCreatedAt(LocalDateTime.now());
        movie22.setUpdatedAt(LocalDateTime.now());
        movie22.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie22);

        MovieEntity movie23 = new MovieEntity();
        movie23.setTitle("Quo Vadis, Aida?");
        movie23.setLanguage("Bosnian");
        movie23.setPgRating("R");
        movie23.setDurationInMinutes(101);
        movie23.setSynopsis("A UN translator tries to save her family during the Srebrenica massacre.");
        movie23.setTrailerUrl("https://example.com/quovadisaida");
        movie23.setStatus("active");
        movie23.setCreatedAt(LocalDateTime.now());
        movie23.setUpdatedAt(LocalDateTime.now());
        movie23.setGenreEntities(List.of(crudGenreRepository.findByName("War"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie23);

        MovieEntity movie24 = new MovieEntity();
        movie24.setTitle("The Perfect Circle");
        movie24.setLanguage("Bosnian");
        movie24.setPgRating("PG-13");
        movie24.setDurationInMinutes(110);
        movie24.setSynopsis("A poet in Sarajevo shelters two orphans during the siege of the city.");
        movie24.setTrailerUrl("https://example.com/perfectcircle");
        movie24.setStatus("active");
        movie24.setCreatedAt(LocalDateTime.now());
        movie24.setUpdatedAt(LocalDateTime.now());
        movie24.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("War")));
        crudMovieRepository.save(movie24);

        MovieEntity movie25 = new MovieEntity();
        movie25.setTitle("An Episode in the Life of an Iron Picker");
        movie25.setLanguage("Bosnian");
        movie25.setPgRating("PG-13");
        movie25.setDurationInMinutes(75);
        movie25.setSynopsis("A poor Roma family faces a medical crisis and struggles for survival.");
        movie25.setTrailerUrl("https://example.com/ironpicker");
        movie25.setStatus("active");
        movie25.setCreatedAt(LocalDateTime.now());
        movie25.setUpdatedAt(LocalDateTime.now());
        movie25.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie25);

        MovieEntity movie26 = new MovieEntity();
        movie26.setTitle("Teško je biti fin");
        movie26.setLanguage("Bosnian");
        movie26.setPgRating("R");
        movie26.setDurationInMinutes(102);
        movie26.setSynopsis("Fudo, a Sarajevo taxi driver, tries to change his life and go straight. But turning away from crime in post-war Bosnia proves difficult.");
        movie26.setTrailerUrl("https://example.com/its-hard-to-be-nice");
        movie26.setStatus("active");
        movie26.setCreatedAt(LocalDateTime.now());
        movie26.setUpdatedAt(LocalDateTime.now());
        movie26.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie26);

        MovieEntity movie27 = new MovieEntity();
        movie27.setTitle("Zlatna dolina");
        movie27.setLanguage("Bosnian");
        movie27.setPgRating("PG-13");
        movie27.setDurationInMinutes(91);
        movie27.setSynopsis("A group of friends in Bosnia decides to pursue quick wealth and excitement, getting tangled in the complex world of crime.");
        movie27.setTrailerUrl("https://example.com/golden-valley");
        movie27.setStatus("active");
        movie27.setCreatedAt(LocalDateTime.now());
        movie27.setUpdatedAt(LocalDateTime.now());
        movie27.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie27);

        List<MovieEntity> allMovies = crudMovieRepository.findAll();

        // Seed projections
        List<ProjectionEntity> projections = new ArrayList<>();
        for (int i = 0; i < allMovies.size(); i++) {
            MovieEntity movie = allMovies.get(i);
            ProjectionEntity projection = new ProjectionEntity();
            projection.setMovieEntity(movie);
            projection.setStatus(i < allMovies.size() / 2 ? "active" : "upcoming"); // Half active, half upcoming

            projections.add(projection);
        }

        // Save projections
        crudProjectionRepository.saveAll(projections);

        // seed venue table
        for (int i = 1; i <= 6; i++) {
            VenueEntity venue = new VenueEntity();
            venue.setName("Sarajevo Venue " + i);
            venue.setStreet("Example Street");
            venue.setStreetNumber(String.valueOf(i));
            venue.setCityEntity(sarajevo);
            venue.setPhone("987-654-3210");
            venue.setCreatedAt(LocalDateTime.now());
            venue.setUpdatedAt(LocalDateTime.now());

            crudVenueRepository.save(venue);
        }

        System.out.println("Database seeded successfully");
    }
//    }
}
