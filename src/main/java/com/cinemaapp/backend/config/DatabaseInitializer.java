package com.cinemaapp.backend.config;

import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.repository.crud.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    private final CrudProjectionRepository crudProjectionRepository;
    private final CrudHallRepository crudHallRepository;
    private final CrudPhotoRepository crudPhotoRepository;


    @Autowired
    public DatabaseInitializer(CrudCityRepository crudCityRepository, CrudGenreRepository crudGenreRepository,
                               CrudVenueRepository crudVenueRepository, CrudMovieRepository crudMovieRepository,
                               CrudUserRepository crudUserRepository, CrudProjectionRepository crudProjectionRepository,
                               CrudHallRepository crudHallRepository, CrudPhotoRepository crudPhotoRepository) {
        this.crudCityRepository = crudCityRepository;
        this.crudGenreRepository = crudGenreRepository;
        this.crudVenueRepository = crudVenueRepository;
        this.crudMovieRepository = crudMovieRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudProjectionRepository = crudProjectionRepository;
        this.crudHallRepository = crudHallRepository;
        this.crudPhotoRepository = crudPhotoRepository;
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
        sciFi.setCreatedAt(LocalDateTime.now());
        sciFi.setUpdatedAt(LocalDateTime.now());
        genres.add(sciFi);

        crudGenreRepository.saveAll(genres);

        // seed movie table
        String[] statuses = {"active", "draft", "archived"};

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
        movie21.setTrailerUrl("https://www.youtube.com/watch?v=nP3ofimxD7Q");
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
        movie22.setTrailerUrl("hhttps://www.youtube.com/watch?v=hQiMvSdLk_4");
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
        movie23.setTrailerUrl("https://www.youtube.com/watch?v=ErLD8P4VUjY");
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
        movie24.setTrailerUrl("https://www.youtube.com/watch?v=0GFyJlptAWY");
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
        movie25.setTrailerUrl("https://www.youtube.com/watch?v=KzSbjPjEUBg");
        movie25.setStatus("active");
        movie25.setCreatedAt(LocalDateTime.now());
        movie25.setUpdatedAt(LocalDateTime.now());
        movie25.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie25);

        MovieEntity movie26 = new MovieEntity();
        movie26.setTitle("It's hard to be nice");
        movie26.setLanguage("Bosnian");
        movie26.setPgRating("R");
        movie26.setDurationInMinutes(102);
        movie26.setSynopsis("Fudo, a Sarajevo taxi driver, tries to change his life and go straight. But turning away from crime in post-war Bosnia proves difficult.");
        movie26.setTrailerUrl("https://www.youtube.com/watch?v=-Kwh2EDIcN4");
        movie26.setStatus("active");
        movie26.setCreatedAt(LocalDateTime.now());
        movie26.setUpdatedAt(LocalDateTime.now());
        movie26.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie26);

        MovieEntity movie27 = new MovieEntity();
        movie27.setTitle("Summer in Golden Walley");
        movie27.setLanguage("Bosnian");
        movie27.setPgRating("PG-13");
        movie27.setDurationInMinutes(91);
        movie27.setSynopsis("A group of friends in Bosnia decides to pursue quick wealth and excitement, getting tangled in the complex world of crime.");
        movie27.setTrailerUrl("https://www.youtube.com/watch?v=UieYbSsqQC0");
        movie27.setStatus("active");
        movie27.setCreatedAt(LocalDateTime.now());
        movie27.setUpdatedAt(LocalDateTime.now());
        movie27.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie27);

        // seed venue table
        VenueEntity venue1 = new VenueEntity();
        venue1.setName("Cinebh: Cineplexx");
        venue1.setStreet("Example Street");
        venue1.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue1.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue1.setPhone("+38733-123-456");
        venue1.setCreatedAt(LocalDateTime.now());
        venue1.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue1);

        HallEntity hall1 = new HallEntity();
        hall1.setName("Hall 1");
        hall1.setVenueEntity(crudVenueRepository.findByName("Cinebh: Cineplexx"));
        hall1.setCreatedAt(LocalDateTime.now());
        hall1.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall1);

        VenueEntity venue2 = new VenueEntity();
        venue2.setName("Cinemacity Sarajevo");
        venue2.setStreet("Maršala Tita");
        venue2.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue2.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue2.setPhone("+38733-321-654");
        venue2.setCreatedAt(LocalDateTime.now());
        venue2.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue2);

        HallEntity hall2 = new HallEntity();
        hall2.setName("Hall 2");
        hall2.setVenueEntity(crudVenueRepository.findByName("Cinemacity Sarajevo"));
        hall2.setCreatedAt(LocalDateTime.now());
        hall2.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall2);

        VenueEntity venue3 = new VenueEntity();
        venue3.setName("Mostar Cinema");
        venue3.setStreet("Kralja Tomislava");
        venue3.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue3.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue3.setPhone("+38736-456-789");
        venue3.setCreatedAt(LocalDateTime.now());
        venue3.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue3);

        HallEntity hall3 = new HallEntity();
        hall3.setName("Hall 3");
        hall3.setVenueEntity(crudVenueRepository.findByName("Mostar Cinema"));
        hall3.setCreatedAt(LocalDateTime.now());
        hall3.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall3);

        VenueEntity venue4 = new VenueEntity();
        venue4.setName("Sarajevo Film Center");
        venue4.setStreet("Obala Kulina Bana");
        venue4.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue4.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue4.setPhone("+38733-654-321");
        venue4.setCreatedAt(LocalDateTime.now());
        venue4.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue4);

        HallEntity hall4 = new HallEntity();
        hall4.setName("Hall 4");
        hall4.setVenueEntity(crudVenueRepository.findByName("Sarajevo Film Center"));
        hall4.setCreatedAt(LocalDateTime.now());
        hall4.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall4);

        VenueEntity venue5 = new VenueEntity();
        venue5.setName("Multiplex Mostar");
        venue5.setStreet("Stjepana Radića");
        venue5.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue5.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue5.setPhone("+38736-987-654");
        venue5.setCreatedAt(LocalDateTime.now());
        venue5.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue5);

        HallEntity hall5 = new HallEntity();
        hall5.setName("Hall 5");
        hall5.setVenueEntity(crudVenueRepository.findByName("Multiplex Mostar"));
        hall5.setCreatedAt(LocalDateTime.now());
        hall5.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall5);

        VenueEntity venue6 = new VenueEntity();
        venue6.setName("CineStar Sarajevo");
        venue6.setStreet("Džemala Bijedića");
        venue6.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue6.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue6.setPhone("+38733-111-222");
        venue6.setCreatedAt(LocalDateTime.now());
        venue6.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue6);

        HallEntity hall6 = new HallEntity();
        hall6.setName("Hall 6");
        hall6.setVenueEntity(crudVenueRepository.findByName("CineStar Sarajevo"));
        hall6.setCreatedAt(LocalDateTime.now());
        hall6.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall6);

        VenueEntity venue7 = new VenueEntity();
        venue7.setName("Cinema City Mostar");
        venue7.setStreet("Kardinala Stepinca");
        venue7.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue7.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue7.setPhone("+38736-333-444");
        venue7.setCreatedAt(LocalDateTime.now());
        venue7.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue7);

        HallEntity hall7 = new HallEntity();
        hall7.setName("Hall 7");
        hall7.setVenueEntity(crudVenueRepository.findByName("Cinema City Mostar"));
        hall7.setCreatedAt(LocalDateTime.now());
        hall7.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall7);

        // Seed projections
        String[] startTimes = {"14:00", "16:00", "18:00", "20:00", "22:15", "23:30"};

        ProjectionEntity projection = new ProjectionEntity();
        projection.setStartDate(LocalDate.now().plusDays(3));
        projection.setEndDate(projection.getStartDate().plusDays(13));
        projection.setHallEntity(crudHallRepository.findByName("Hall 1"));
        projection.setMovieEntity(crudMovieRepository.findByTitle("It's hard to be nice"));
        projection.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4]});
        projection.setCreatedAt(LocalDateTime.now());
        projection.setUpdatedAt(LocalDateTime.now());
        projection.setStatus("active");

        crudProjectionRepository.save(projection);

        ProjectionEntity projection1 = new ProjectionEntity();
        projection1.setHallEntity(crudHallRepository.findByName("Hall 1"));
        projection1.setStartDate(LocalDate.now().plusDays(1));
        projection1.setEndDate(LocalDate.now().plusDays(11));
        projection1.setMovieEntity(crudMovieRepository.findByTitle("Avatar"));
        projection1.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4]});
        projection1.setStatus("upcoming");
        projection1.setCreatedAt(LocalDateTime.now());
        projection1.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection1);

        ProjectionEntity projection2 = new ProjectionEntity();
        projection2.setHallEntity(crudHallRepository.findByName("Hall 2"));
        projection2.setStartDate(LocalDate.now().plusDays(1));
        projection2.setEndDate(LocalDate.now().plusDays(11));
        projection2.setMovieEntity(crudMovieRepository.findByTitle("Inception"));
        projection2.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4]});
        projection2.setStatus("active");
        projection2.setCreatedAt(LocalDateTime.now());
        projection2.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection2);

        ProjectionEntity projection3 = new ProjectionEntity();
        projection3.setHallEntity(crudHallRepository.findByName("Hall 3"));
        projection3.setStartDate(LocalDate.now().plusDays(7));
        projection3.setEndDate(LocalDate.now().plusDays(17));
        projection3.setMovieEntity(crudMovieRepository.findByTitle("Titanic"));
        projection3.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4], startTimes[5]});
        projection3.setStatus("active");
        projection3.setCreatedAt(LocalDateTime.now());
        projection3.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection3);

        ProjectionEntity projection4 = new ProjectionEntity();
        projection4.setHallEntity(crudHallRepository.findByName("Hall 4"));
        projection4.setStartDate(LocalDate.now().plusDays(9));
        projection4.setEndDate(LocalDate.now().plusDays(19));
        projection4.setMovieEntity(crudMovieRepository.findByTitle("The Dark Knight"));
        projection4.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[5]});
        projection4.setStatus("active");
        projection4.setCreatedAt(LocalDateTime.now());
        projection4.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection4);

        ProjectionEntity projection5 = new ProjectionEntity();
        projection5.setHallEntity(crudHallRepository.findByName("Hall 5"));
        projection5.setStartDate(LocalDate.now().plusDays(0));
        projection5.setEndDate(LocalDate.now().plusDays(10));
        projection5.setMovieEntity(crudMovieRepository.findByTitle("The Matrix"));
        projection5.setStartTime(new String[]{startTimes[2], startTimes[3], startTimes[4]});
        projection5.setStatus("active");
        projection5.setCreatedAt(LocalDateTime.now());
        projection5.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection5);

        ProjectionEntity projection6 = new ProjectionEntity();
        projection6.setHallEntity(crudHallRepository.findByName("Hall 6"));
        projection6.setStartDate(LocalDate.now().plusDays(11));
        projection6.setEndDate(LocalDate.now().plusDays(21));
        projection6.setMovieEntity(crudMovieRepository.findByTitle("Interstellar"));
        projection6.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[5]});
        projection6.setStatus("upcoming");
        projection6.setCreatedAt(LocalDateTime.now());
        projection6.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection6);

        ProjectionEntity projection7 = new ProjectionEntity();
        projection7.setHallEntity(crudHallRepository.findByName("Hall 7"));
        projection7.setStartDate(LocalDate.now().plusDays(15));
        projection7.setEndDate(LocalDate.now().plusDays(25));
        projection7.setMovieEntity(crudMovieRepository.findByTitle("Gladiator"));
        projection7.setStartTime(new String[]{startTimes[2], startTimes[3], startTimes[4]});
        projection7.setStatus("upcoming");
        projection7.setCreatedAt(LocalDateTime.now());
        projection7.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection7);

        ProjectionEntity projection8 = new ProjectionEntity();
        projection8.setHallEntity(crudHallRepository.findByName("Hall 1"));
        projection8.setStartDate(LocalDate.now().plusDays(1));
        projection8.setEndDate(LocalDate.now().plusDays(11));
        projection8.setMovieEntity(crudMovieRepository.findByTitle("The Godfather"));
        projection8.setStartTime(new String[]{startTimes[5]});
        projection8.setStatus("active");
        projection8.setCreatedAt(LocalDateTime.now());
        projection8.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection8);

        ProjectionEntity projection9 = new ProjectionEntity();
        projection9.setHallEntity(crudHallRepository.findByName("Hall 2"));
        projection9.setStartDate(LocalDate.now().plusDays(12));
        projection9.setEndDate(LocalDate.now().plusDays(22));
        projection9.setMovieEntity(crudMovieRepository.findByTitle("Pulp Fiction"));
        projection9.setStartTime(new String[]{startTimes[2], startTimes[3], startTimes[4], startTimes[5]});
        projection9.setStatus("upcoming");
        projection9.setCreatedAt(LocalDateTime.now());
        projection9.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection9);

        ProjectionEntity projection10 = new ProjectionEntity();
        projection10.setHallEntity(crudHallRepository.findByName("Hall 3"));
        projection10.setStartDate(LocalDate.now().plusDays(1));
        projection10.setEndDate(LocalDate.now().plusDays(5));
        projection10.setMovieEntity(crudMovieRepository.findByTitle("Schindler's List"));
        projection10.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3]});
        projection10.setStatus("active");
        projection10.setCreatedAt(LocalDateTime.now());
        projection10.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection10);

        ProjectionEntity projection11 = new ProjectionEntity();
        projection11.setHallEntity(crudHallRepository.findByName("Hall 4"));
        projection11.setStartDate(LocalDate.now().plusDays(0));
        projection11.setEndDate(LocalDate.now().plusDays(9));
        projection11.setMovieEntity(crudMovieRepository.findByTitle("Fight Club"));
        projection11.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[4]});
        projection11.setStatus("active");
        projection11.setCreatedAt(LocalDateTime.now());
        projection11.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection11);

        ProjectionEntity projection12 = new ProjectionEntity();
        projection12.setHallEntity(crudHallRepository.findByName("Hall 5"));
        projection12.setStartDate(LocalDate.now().plusDays(10));
        projection12.setEndDate(LocalDate.now().plusDays(20));
        projection12.setMovieEntity(crudMovieRepository.findByTitle("Forrest Gump"));
        projection12.setStartTime(new String[]{startTimes[3], startTimes[4], startTimes[5]});
        projection12.setStatus("upcoming");
        projection12.setCreatedAt(LocalDateTime.now());
        projection12.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection12);

        ProjectionEntity projection13 = new ProjectionEntity();
        projection13.setHallEntity(crudHallRepository.findByName("Hall 6"));
        projection13.setStartDate(LocalDate.now().plusDays(0));
        projection13.setEndDate(LocalDate.now().plusDays(10));
        projection13.setMovieEntity(crudMovieRepository.findByTitle("The Lion King"));
        projection13.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4], startTimes[5]});
        projection13.setStatus("active");
        projection13.setCreatedAt(LocalDateTime.now());
        projection13.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection13);

        ProjectionEntity projection14 = new ProjectionEntity();
        projection14.setHallEntity(crudHallRepository.findByName("Hall 7"));
        projection14.setStartDate(LocalDate.now().plusDays(1));
        projection14.setEndDate(LocalDate.now().plusDays(11));
        projection14.setMovieEntity(crudMovieRepository.findByTitle("Saving Private Ryan"));
        projection14.setStartTime(new String[]{startTimes[1], startTimes[2], startTimes[3], startTimes[4]});
        projection14.setStatus("active");
        projection14.setCreatedAt(LocalDateTime.now());
        projection14.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection14);

        ProjectionEntity projection15 = new ProjectionEntity();
        projection15.setHallEntity(crudHallRepository.findByName("Hall 1"));
        projection15.setStartDate(LocalDate.now().plusDays(13));
        projection15.setEndDate(LocalDate.now().plusDays(23));
        projection15.setMovieEntity(crudMovieRepository.findByTitle("The Shawshank Redemption"));
        projection15.setStartTime(new String[]{startTimes[5]});
        projection15.setStatus("upcoming");
        projection15.setCreatedAt(LocalDateTime.now());
        projection15.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection15);

        ProjectionEntity projection16 = new ProjectionEntity();
        projection16.setHallEntity(crudHallRepository.findByName("Hall 7"));
        projection16.setStartDate(LocalDate.now().plusDays(5));
        projection16.setEndDate(LocalDate.now().plusDays(12));
        projection16.setMovieEntity(crudMovieRepository.findByTitle("The Silence of the Lambs"));
        projection16.setStartTime(new String[]{startTimes[0]});
        projection16.setStatus("active");
        projection16.setCreatedAt(LocalDateTime.now());
        projection16.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection16);

        ProjectionEntity projection17 = new ProjectionEntity();
        projection17.setHallEntity(crudHallRepository.findByName("Hall 4"));
        projection17.setStartDate(LocalDate.now().plusDays(2));
        projection17.setEndDate(LocalDate.now().plusDays(11));
        projection17.setMovieEntity(crudMovieRepository.findByTitle("Grbavica"));
        projection17.setStartTime(new String[]{startTimes[5], startTimes[2]});
        projection17.setStatus("active");
        projection17.setCreatedAt(LocalDateTime.now());
        projection17.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection17);

        ProjectionEntity projection18 = new ProjectionEntity();
        projection18.setHallEntity(crudHallRepository.findByName("Hall 2"));
        projection18.setStartDate(LocalDate.now().plusDays(3));
        projection18.setEndDate(LocalDate.now().plusDays(10));
        projection18.setMovieEntity(crudMovieRepository.findByTitle("The Perfect Circle"));
        projection18.setStartTime(new String[]{ startTimes[3], startTimes[5]});
        projection18.setStatus("active");
        projection18.setCreatedAt(LocalDateTime.now());
        projection18.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection18);

        ProjectionEntity projection19 = new ProjectionEntity();
        projection19.setHallEntity(crudHallRepository.findByName("Hall 6"));
        projection19.setStartDate(LocalDate.now().plusDays(7));
        projection19.setEndDate(LocalDate.now().plusDays(15));
        projection19.setMovieEntity(crudMovieRepository.findByTitle("Green Mile"));
        projection19.setStartTime(new String[]{startTimes[5]});
        projection19.setStatus("active");
        projection19.setCreatedAt(LocalDateTime.now());
        projection19.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection19);

        ProjectionEntity projection20 = new ProjectionEntity();
        projection20.setHallEntity(crudHallRepository.findByName("Hall 3"));
        projection20.setStartDate(LocalDate.now().plusDays(2));
        projection20.setEndDate(LocalDate.now().plusDays(10));
        projection20.setMovieEntity(crudMovieRepository.findByTitle("Summer in Golden Walley"));
        projection20.setStartTime(new String[]{startTimes[5]});
        projection20.setStatus("active");
        projection20.setCreatedAt(LocalDateTime.now());
        projection20.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection20);

        ProjectionEntity projection21 = new ProjectionEntity();
        projection21.setHallEntity(crudHallRepository.findByName("Hall 5"));
        projection21.setStartDate(LocalDate.now().plusDays(1));
        projection21.setEndDate(LocalDate.now().plusDays(5));
        projection21.setMovieEntity(crudMovieRepository.findByTitle("The Lord of the Rings: The Fellowship of the Ring"));
        projection21.setStartTime(new String[]{ startTimes[4], startTimes[5]});
        projection21.setStatus("active");
        projection21.setCreatedAt(LocalDateTime.now());
        projection21.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection21);

        ProjectionEntity projection22 = new ProjectionEntity();
        projection22.setHallEntity(crudHallRepository.findByName("Hall 2"));
        projection22.setStartDate(LocalDate.now().plusDays(3));
        projection22.setEndDate(LocalDate.now().plusDays(12));
        projection22.setMovieEntity(crudMovieRepository.findByTitle("Jurassic Park"));
        projection22.setStartTime(new String[]{ startTimes[0], startTimes[5]});
        projection22.setStatus("active");
        projection22.setCreatedAt(LocalDateTime.now());
        projection22.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection22);

        ProjectionEntity projection23 = new ProjectionEntity();
        projection23.setHallEntity(crudHallRepository.findByName("Hall 7"));
        projection23.setStartDate(LocalDate.now().plusDays(5));
        projection23.setEndDate(LocalDate.now().plusDays(15));
        projection23.setMovieEntity(crudMovieRepository.findByTitle("The Shawshank Redemption"));
        projection23.setStartTime(new String[]{startTimes[4]});
        projection23.setStatus("active");
        projection23.setCreatedAt(LocalDateTime.now());
        projection23.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection23);

        // seed photo table
        PhotoEntity photo1 = new PhotoEntity();
        photo1.setEntityType("movie");
        photo1.setRefEntityId(crudMovieRepository.findByTitle("Avatar").getId());
        photo1.setUrl("https://www.usmagazine.com/wp-content/uploads/2022/07/James-Cameron-Warns-Avatar-2-Is-3-Hours-Its-OK-Get-Up-Go-Pee.jpg?quality=86&strip=all");
        crudPhotoRepository.save(photo1);

        PhotoEntity photo2 = new PhotoEntity();
        photo2.setEntityType("movie");
        photo2.setRefEntityId(crudMovieRepository.findByTitle("Inception").getId());
        photo2.setUrl("https://nextbestpicture-com.b-cdn.net/wp-content/uploads/2024/04/Inception.jpg");
        crudPhotoRepository.save(photo2);

        PhotoEntity photo3 = new PhotoEntity();
        photo3.setEntityType("movie");
        photo3.setRefEntityId(crudMovieRepository.findByTitle("Titanic").getId());
        photo3.setUrl("https://www.reddit.com/media?url=https%3A%2F%2Fpreview.redd.it%2Fx7j3qgrlh3z11.jpg%3Fwidth%3D1080%26crop%3Dsmart%26auto%3Dwebp%26s%3D47e0c2b18967057dda38f9e8b8bf03b42fa9ecee");
        crudPhotoRepository.save(photo3);

        PhotoEntity photo4 = new PhotoEntity();
        photo4.setEntityType("movie");
        photo4.setRefEntityId(crudMovieRepository.findByTitle("The Dark Knight").getId());
        photo4.setUrl("https://www.prime1studio.com/on/demandware.static/-/Sites-p1s-master-catalog/default/dwe65eb6a7/images/HDMMDC-02/media/hdmmdc-02_a19.jpg");
        crudPhotoRepository.save(photo4);

        PhotoEntity photo5 = new PhotoEntity();
        photo5.setEntityType("movie");
        photo5.setRefEntityId(crudMovieRepository.findByTitle("The Matrix").getId());
        photo5.setUrl("https://storage.googleapis.com/pod_public/1300/106922.jpg");
        crudPhotoRepository.save(photo5);

        PhotoEntity photo6 = new PhotoEntity();
        photo6.setEntityType("movie");
        photo6.setRefEntityId(crudMovieRepository.findByTitle("Interstellar").getId());
        photo6.setUrl("https://www.seacoastonline.com/gcdn/authoring/2014/11/07/NPOH/ghows-SO-00f35d1e-def9-4ba8-9155-d936bed5f192-b9e187d3.jpeg?width=660&height=413&fit=crop&format=pjpg&auto=webp");
        crudPhotoRepository.save(photo6);

        PhotoEntity photo7 = new PhotoEntity();
        photo7.setEntityType("movie");
        photo7.setRefEntityId(crudMovieRepository.findByTitle("Gladiator").getId());
        photo7.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/0*TxZdqJf0MQ2mdL9p.jpg");
        crudPhotoRepository.save(photo7);

        PhotoEntity photo8 = new PhotoEntity();
        photo8.setEntityType("movie");
        photo8.setRefEntityId(crudMovieRepository.findByTitle("The Godfather").getId());
        photo8.setUrl("https://jerseymanmagazine.com/wp-content/uploads/2022/04/The-Godfather-e1648766305891-900x600.jpg");
        crudPhotoRepository.save(photo8);

        PhotoEntity photo9 = new PhotoEntity();
        photo9.setEntityType("movie");
        photo9.setRefEntityId(crudMovieRepository.findByTitle("Pulp Fiction").getId());
        photo9.setUrl("https://www.closeup-shop.com/media/oart_0/oart_p/oart_13731/thumbs/1277574_4609821.jpg");
        crudPhotoRepository.save(photo9);

        PhotoEntity photo10 = new PhotoEntity();
        photo10.setEntityType("movie");
        photo10.setRefEntityId(crudMovieRepository.findByTitle("Schindler's List").getId());
        photo10.setUrl("https://goldendiscs.ie/cdn/shop/products/817sLmprCSL._AC_SY445.jpg?v=1690429826");
        crudPhotoRepository.save(photo10);

        PhotoEntity photo11 = new PhotoEntity();
        photo11.setEntityType("movie");
        photo11.setRefEntityId(crudMovieRepository.findByTitle("Fight Club").getId());
        photo11.setUrl("https://static.wikia.nocookie.net/listofdeaths/images/2/26/Fight_Club.jpg/revision/latest?cb=20220911154126");
        crudPhotoRepository.save(photo11);

        PhotoEntity photo12 = new PhotoEntity();
        photo12.setEntityType("movie");
        photo12.setRefEntityId(crudMovieRepository.findByTitle("Forrest Gump").getId());
        photo12.setUrl("https://ntvb.tmsimg.com/assets/p15829_v_h8_aw.jpg?w=1280&h=720");
        crudPhotoRepository.save(photo12);

        PhotoEntity photo13 = new PhotoEntity();
        photo13.setEntityType("movie");
        photo13.setRefEntityId(crudMovieRepository.findByTitle("The Lion King").getId());
        photo13.setUrl("https://m.media-amazon.com/images/I/51UjjVDiEDL._SX342_SY445_.jpg");
        crudPhotoRepository.save(photo13);

        PhotoEntity photo14 = new PhotoEntity();
        photo14.setEntityType("movie");
        photo14.setRefEntityId(crudMovieRepository.findByTitle("Saving Private Ryan").getId());
        photo14.setUrl("https://www.soundandvision.com/images/styles/600_wide/public/110722_director%27s_intent_saving_pvt_ryan_promo.png");
        crudPhotoRepository.save(photo14);

        PhotoEntity photo15 = new PhotoEntity();
        photo15.setEntityType("movie");
        photo15.setRefEntityId(crudMovieRepository.findByTitle("The Shawshank Redemption").getId());
        photo15.setUrl("https://static1.srcdn.com/wordpress/wp-content/uploads/2023/12/red-and-andy-in-shawshank-redemption.jpg?q=70&fit=crop&w=1140&h=&dpr=1");
        crudPhotoRepository.save(photo15);

        PhotoEntity photo16 = new PhotoEntity();
        photo16.setEntityType("movie");
        photo16.setRefEntityId(crudMovieRepository.findByTitle("The Green Mile").getId());
        photo16.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/1*oVTrOMrJv9FwP2WecfEG-g.png");
        crudPhotoRepository.save(photo16);

        PhotoEntity photo17 = new PhotoEntity();
        photo17.setEntityType("movie");
        photo17.setRefEntityId(crudMovieRepository.findByTitle("The Avengers").getId());
        photo17.setUrl("https://cdn.marvel.com/content/1x/avengersendgame_lob_crd_05.jpg");
        crudPhotoRepository.save(photo17);

        PhotoEntity photo18 = new PhotoEntity();
        photo18.setEntityType("movie");
        photo18.setRefEntityId(crudMovieRepository.findByTitle("Jurassic Park").getId());
        photo18.setUrl("https://m.media-amazon.com/images/I/61W7iD5+XKL._SX342_SY445_.jpg");
        crudPhotoRepository.save(photo18);

        PhotoEntity photo19 = new PhotoEntity();
        photo19.setEntityType("movie");
        photo19.setRefEntityId(crudMovieRepository.findByTitle("The Silence of the Lambs").getId());
        photo19.setUrl("https://images.bauerhosting.com/legacy/media/6026/a037/4ef6/302f/a23a/5c22/sotl-3.jpg?ar=16%3A9&fit=crop&crop=top&auto=format&w=992&q=80");
        crudPhotoRepository.save(photo19);

        PhotoEntity photo20 = new PhotoEntity();
        photo20.setEntityType("movie");
        photo20.setRefEntityId(crudMovieRepository.findByTitle("The Lord of the Rings: The Fellowship of the Ring").getId());
        photo20.setUrl("https://images.cdn.prd.api.discomax.com/2023/05/05/be192083-0dfc-3df5-bba2-67725ab6b0fc.jpeg?f=jpg&q=75&w=1280&w=1200");
        crudPhotoRepository.save(photo20);

        PhotoEntity photo21 = new PhotoEntity();
        photo21.setEntityType("movie");
        photo21.setRefEntityId(crudMovieRepository.findByTitle("No Man's Land").getId());
        photo21.setUrl("https://upload.wikimedia.org/wikipedia/sr/3/3a/Nicija_zemlja.jpg");
        crudPhotoRepository.save(photo21);

        PhotoEntity photo22 = new PhotoEntity();
        photo22.setEntityType("movie");
        photo22.setRefEntityId(crudMovieRepository.findByTitle("Grbavica").getId());
        photo22.setUrl("https://upload.wikimedia.org/wikipedia/bs/5/56/Grbavica_poster.jpg");
        crudPhotoRepository.save(photo22);

        PhotoEntity photo23 = new PhotoEntity();
        photo23.setEntityType("movie");
        photo23.setRefEntityId(crudMovieRepository.findByTitle("Quo Vadis, Aida?").getId());
        photo23.setUrl("https://pad.mymovies.it/filmclub/2020/07/230/locandina.jpg");
        crudPhotoRepository.save(photo23);

        PhotoEntity photo24 = new PhotoEntity();
        photo24.setEntityType("movie");
        photo24.setRefEntityId(crudMovieRepository.findByTitle("The Perfect Circle").getId());
        photo24.setUrl("https://avaz.ba/media/2022/07/19/1867142/adis-film.jpg");
        crudPhotoRepository.save(photo24);

        PhotoEntity photo25 = new PhotoEntity();
        photo25.setEntityType("movie");
        photo25.setRefEntityId(crudMovieRepository.findByTitle("An Episode in the Life of an Iron Picker").getId());
        photo25.setUrl("https://upload.wikimedia.org/wikipedia/bs/e/e6/Epizoda_u_%C5%BEivotu_bera%C4%8Da_%C5%BEeljeza.jpg");
        crudPhotoRepository.save(photo25);

        PhotoEntity photo26 = new PhotoEntity();
        photo26.setEntityType("movie");
        photo26.setRefEntityId(crudMovieRepository.findByTitle("It's hard to be nice").getId());
        photo26.setUrl("https://3.bp.blogspot.com/-9Qc3LcjZ3wA/VFJGtny62cI/AAAAAAAAB5c/Wu7DXAW37AE/s1600/tesko-je-biti-fin.jpg");
        crudPhotoRepository.save(photo26);

        PhotoEntity photo27 = new PhotoEntity();
        photo27.setEntityType("movie");
        photo27.setRefEntityId(crudMovieRepository.findByTitle("Summer in Golden Walley").getId());
        photo27.setUrl("https://www.filmofil.ba/images/content/photo-gallery/Ljeto_u_zlatnoj_dolini_21674920786.jpg");
        crudPhotoRepository.save(photo27);

        System.out.println("Database seeded successfully");
    }
//    }
}
