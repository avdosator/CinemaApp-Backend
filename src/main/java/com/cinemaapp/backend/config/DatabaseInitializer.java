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
        movie1.setDirector("James Cameron");
        movie1.setPgRating("PG-13");
        movie1.setDurationInMinutes(162);
        movie1.setWriters(new String[]{"James Cameron"});
        movie1.setActors(new String[]{
                "Sam Worthington/Jake Sully",
                "Zoe Saldana/Neytiri",
                "Sigourney Weaver/Dr. Grace",
                "Stephen Lang/Colonel",
                "Giovanni Ribisi/Parker",
                "Michelle Rodriguez/Trudy"
        });
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
        movie2.setDirector("Christopher Nolan");
        movie2.setPgRating("PG-13");
        movie2.setDurationInMinutes(148);
        movie2.setWriters(new String[]{"Christopher Nolan"});
        movie2.setActors(new String[]{
                "Leonardo DiCaprio/Dom",
                "Joseph Gordon-Levitt/Arthur",
                "Ellen Page/Ariadne",
                "Tom Hardy/Eames",
                "Ken Watanabe/Saito",
                "Cillian Murphy/Robert"
        });
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
        movie3.setDirector("James Cameron");
        movie3.setPgRating("PG-13");
        movie3.setDurationInMinutes(195);
        movie3.setWriters(new String[]{"James Cameron"});
        movie3.setActors(new String[]{
                "Leonardo DiCaprio/Jack",
                "Kate Winslet/Rose",
                "Billy Zane/Cal",
                "Kathy Bates/Molly",
                "Frances Fisher/Ruth",
                "Danny Nucci/Fabrizio"
        });
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
        movie4.setDirector("Christopher Nolan");
        movie4.setPgRating("PG-13");
        movie4.setDurationInMinutes(152);
        movie4.setWriters(new String[]{"Jonathan Nolan", "Christopher Nolan", "David S. Goyer"});
        movie4.setActors(new String[]{
                "Christian Bale/Bruce",
                "Heath Ledger/Joker",
                "Aaron Eckhart/Harvey",
                "Michael Caine/Alfred",
                "Maggie Gyllenhaal/Rachel",
                "Gary Oldman/Jim"
        });
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
        movie5.setDirector("The Wachowskis");
        movie5.setPgRating("R");
        movie5.setDurationInMinutes(136);
        movie5.setWriters(new String[]{"Lana Wachowski", "Lilly Wachowski"});
        movie5.setActors(new String[]{
                "Keanu Reeves/Neo",
                "Laurence Fishburne/Morpheus",
                "Carrie-Anne Moss/Trinity",
                "Hugo Weaving/Smith",
                "Gloria Foster/The Oracle",
                "Joe Pantoliano/Cypher"
        });
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
        movie6.setDirector("Christopher Nolan");
        movie6.setPgRating("PG-13");
        movie6.setDurationInMinutes(169);
        movie6.setWriters(new String[]{"Jonathan Nolan", "Christopher Nolan"});
        movie6.setActors(new String[]{
                "Matthew McConaughey/Cooper",
                "Anne Hathaway/Brand",
                "Jessica Chastain/Murph",
                "Michael Caine/Brand",
                "Casey Affleck/Tom",
                "Mackenzie Foy/Murph"
        });
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
        movie7.setDirector("Ridley Scott");
        movie7.setPgRating("R");
        movie7.setDurationInMinutes(155);
        movie7.setWriters(new String[]{"David Franzoni", "John Logan", "William Nicholson"});
        movie7.setActors(new String[]{
                "Russell Crowe/Maximus",
                "Joaquin Phoenix/Commodus",
                "Connie Nielsen/Lucilla",
                "Oliver Reed/Proximo",
                "Derek Jacobi/Gracchus",
                "Djimon Hounsou/Juba"
        });
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
        movie8.setDirector("Francis Ford Coppola");
        movie8.setPgRating("R");
        movie8.setDurationInMinutes(175);
        movie8.setWriters(new String[]{"Mario Puzo", "Francis Ford Coppola"});
        movie8.setActors(new String[]{
                "Marlon Brando/Vito",
                "Al Pacino/Michael",
                "James Caan/Sonny",
                "Richard S. Castellano/Clemenza",
                "Robert Duvall/Tom",
                "Diane Keaton/Kay"
        });
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
        movie9.setDirector("Quentin Tarantino");
        movie9.setPgRating("R");
        movie9.setDurationInMinutes(154);
        movie9.setWriters(new String[]{"Quentin Tarantino", "Roger Avary"});
        movie9.setActors(new String[]{
                "John Travolta/Vincent",
                "Uma Thurman/Mia",
                "Samuel L. Jackson/Jules",
                "Bruce Willis/Butch",
                "Ving Rhames/Marsellus",
                "Harvey Keitel/Winston"
        });
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
        movie10.setDirector("Steven Spielberg");
        movie10.setPgRating("R");
        movie10.setDurationInMinutes(195);
        movie10.setWriters(new String[]{"Steven Zaillian"});
        movie10.setActors(new String[]{
                "Liam Neeson/Oskar",
                "Ralph Fiennes/Amon",
                "Ben Kingsley/Itzhak",
                "Caroline Goodall/Emilie",
                "Jonathan Sagall/Poldek",
                "Embeth Davidtz/Helen"
        });
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
        movie11.setDirector("David Fincher");
        movie11.setPgRating("R");
        movie11.setDurationInMinutes(139);
        movie11.setWriters(new String[]{"Jim Uhls", "Chuck Palahniuk"});
        movie11.setActors(new String[]{
                "Brad Pitt/Tyler",
                "Edward Norton/Narrator",
                "Helena Carter/Marla",
                "Meat Loaf/Robert",
                "Jared Leto/Angel Face",
                "Zach Grenier/Richard"
        });
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
        movie12.setDirector("Robert Zemeckis");
        movie12.setPgRating("PG-13");
        movie12.setDurationInMinutes(142);
        movie12.setWriters(new String[]{"Winston Groom", "Eric Roth"});
        movie12.setActors(new String[]{
                "Tom Hanks/Forrest",
                "Robin Wright/Jenny",
                "Gary Sinise/Dan",
                "Sally Field/Mrs. Gump",
                "Mykelti Williamson/Bubba",
                "Michael Conner Humphreys/Young Forrest"
        });
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
        movie13.setDirector("Roger Allers");
        movie13.setPgRating("G");
        movie13.setDurationInMinutes(88);
        movie13.setWriters(new String[]{"Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"});
        movie13.setActors(new String[]{
                "Matthew Broderick/Simba",
                "Jeremy Irons/Scar",
                "James Earl Jones/Mufasa",
                "Moira Kelly/Nala",
                "Rowan Atkinson/Zazu",
                "Whoopi Goldberg/Shenzi"
        });
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
        movie14.setDirector("Steven Spielberg");
        movie14.setPgRating("R");
        movie14.setDurationInMinutes(169);
        movie14.setWriters(new String[]{"Robert Rodat"});
        movie14.setActors(new String[]{
                "Tom Hanks/John",
                "Matt Damon/James",
                "Tom Sizemore/Mike",
                "Edward Burns/Richard",
                "Barry Pepper/Daniel",
                "Giovanni Ribisi/Irwin"
        });
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
        movie15.setDirector("Frank Darabont");
        movie15.setPgRating("R");
        movie15.setDurationInMinutes(142);
        movie15.setWriters(new String[]{"Stephen King", "Frank Darabont"});
        movie15.setActors(new String[]{
                "Tim Robbins/Andy",
                "Morgan Freeman/Red",
                "Bob Gunton/Norton",
                "William Sadler/Heywood",
                "Clancy Brown/Hadley",
                "Gil Bellows/Tommy"
        });
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
        movie16.setDirector("Frank Darabont");
        movie16.setPgRating("R");
        movie16.setDurationInMinutes(189);
        movie16.setWriters(new String[]{"Stephen King", "Frank Darabont"});
        movie16.setActors(new String[]{
                "Tom Hanks/Paul",
                "Michael Duncan/John",
                "David Morse/Brutal",
                "Bonnie Hunt/Jan",
                "James Cromwell/Hal",
                "Doug Hutchison/Percy"
        });
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
        movie17.setDirector("Joss Whedon");
        movie17.setPgRating("PG-13");
        movie17.setDurationInMinutes(143);
        movie17.setWriters(new String[]{"Joss Whedon", "Zak Penn"});
        movie17.setActors(new String[]{
                "Robert Downey Jr./Iron Man",
                "Chris Evans/Cpt. America",
                "Mark Ruffalo/Bruce",
                "Chris Hemsworth/Thor",
                "Scarlett Johansson/Black Widow",
                "Jeremy Renner/Hawkeye"
        });
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
        movie18.setDirector("Steven Spielberg");
        movie18.setPgRating("PG-13");
        movie18.setDurationInMinutes(127);
        movie18.setWriters(new String[]{"Michael Crichton", "David Koepp"});
        movie18.setActors(new String[]{
                "Sam Neill/Alan",
                "Laura Dern/Ellie",
                "Jeff Goldblum/Ian",
                "Richard Attenborough/John",
                "Bob Peck/Robert",
                "Martin Ferrero/Donald"
        });
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
        movie19.setDirector("Jonathan Demme");
        movie19.setPgRating("R");
        movie19.setDurationInMinutes(118);
        movie19.setWriters(new String[]{"Thomas Harris", "Ted Tally"});
        movie19.setActors(new String[]{
                "Jodie Foster/Clarice",
                "Anthony Hopkins/Hannibal",
                "Scott Glenn/Jack",
                "Ted Levine/Jame",
                "Anthony Heald/Frederick",
                "Brooke Smith/Catherine"
        });
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
        movie20.setDirector("Peter Jackson");
        movie20.setPgRating("PG-13");
        movie20.setDurationInMinutes(178);
        movie20.setWriters(new String[]{"J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens", "Peter Jackson"});
        movie20.setActors(new String[]{
                "Elijah Wood/Frodo",
                "Ian McKellen/Gandalf",
                "Viggo Mortensen/Aragorn",
                "Sean Astin/Samwise",
                "Orlando Bloom/Legolas",
                "John Rhys-Davies/Gimli"
        });
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
        movie21.setDirector("Danis Tanović");
        movie21.setPgRating("R");
        movie21.setDurationInMinutes(98);
        movie21.setWriters(new String[]{"Danis Tanović"});
        movie21.setActors(new String[]{
                "Branko Đurić/Čiki",
                "Rene Bitorajac/Nino",
                "Filip Šovagović/Cera",
                "Georges Siatidis/Marchand",
                "Katrin Cartlidge/Jane",
                "Simon Callow/Soft"
        });
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
        movie22.setDirector("Jasmila Žbanić");
        movie22.setPgRating("PG-13");
        movie22.setDurationInMinutes(107);
        movie22.setWriters(new String[]{"Jasmila Žbanić"});
        movie22.setActors(new String[]{
                "Mirjana Karanović/Esma",
                "Luna Mijović/Sara",
                "Leon Lučev/Pelikan",
                "Kenan Ćatić/Samil",
                "Dejan Aćimović/Cengo",
                "Jasna Beri/Rabija"
        });
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
        movie23.setDirector("Jasmila Žbanić");
        movie23.setPgRating("R");
        movie23.setDurationInMinutes(101);
        movie23.setWriters(new String[]{"Jasmila Žbanić"});
        movie23.setActors(new String[]{
                "Jasna Đuričić/Aida",
                "Izudin Bajrović/Nihad",
                "Boris Ler/Hamid",
                "Dino Bajrović/Sejo",
                "Johan Heldenbergh/Thor",
                "Raymond Thiry/General"
        });
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
        movie24.setDirector("Ademir Kenović");
        movie24.setPgRating("PG-13");
        movie24.setDurationInMinutes(110);
        movie24.setActors(new String[]{
                "Josip Pejaković/Hamza",
                "Almedin Leleta/Adis",
                "Alma Terzić/Azra",
                "Jasna Diklić/Wife",
                "Dragan Marinković/Dado",
                "Minka Muftić/Ruža"
        });
        movie24.setSynopsis("A poet in Sarajevo shelters two orphans during the siege of the city.");
        movie24.setTrailerUrl("https://www.youtube.com/watch?v=0GFyJlptAWY");
        movie24.setStatus("active");
        movie24.setCreatedAt(LocalDateTime.now());
        movie24.setUpdatedAt(LocalDateTime.now());
        movie24.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("War")));
        crudMovieRepository.save(movie24);
        movie24.setWriters(new String[]{"Abdulah Sidran", "Ademir Kenović"});

        MovieEntity movie25 = new MovieEntity();
        movie25.setTitle("An Episode in the Life of an Iron Picker");
        movie25.setLanguage("Bosnian");
        movie25.setDirector("Danis Tanović");
        movie25.setPgRating("PG-13");
        movie25.setDurationInMinutes(75);
        movie25.setWriters(new String[]{"Danis Tanović"});
        movie25.setActors(new String[]{
                "Nazif Mujić/Nazif",
                "Senada Alimanović/Senada",
                "Šemsa Mujić/Šemsa",
                "Sandra Mujić/Sandra",
                "Elvedin Mrkonjić/Elvedin",
                "Ivica Mrkonjić/Ivica"
        });
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
        movie26.setWriters(new String[]{"Srđan Vuletić"});
        movie26.setActors(new String[]{
                "Saša Petrović/Fudo",
                "Daria Lorenci/Dolina",
                "Emir Hadžihafizbegović/Aleksandar",
                "Zana Marjanović/Kanita",
                "Aleksandar Seksan/Ratko",
                "Mirsad Tuka/Rosko"
        });
        movie26.setDirector("Srđan Vuletić");
        movie26.setSynopsis("Fudo, a Sarajevo taxi driver, tries to change his life and go straight. But turning away from crime in post-war Bosnia proves difficult.");
        movie26.setTrailerUrl("https://www.youtube.com/watch?v=-Kwh2EDIcN4");
        movie26.setStatus("active");
        movie26.setCreatedAt(LocalDateTime.now());
        movie26.setUpdatedAt(LocalDateTime.now());
        movie26.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie26);

        MovieEntity movie27 = new MovieEntity();
        movie27.setTitle("Summer in Golden Valley");
        movie27.setLanguage("Bosnian");
        movie27.setDirector("Srđan Vuletić");
        movie27.setPgRating("PG-13");
        movie27.setDurationInMinutes(91);
        movie27.setWriters(new String[]{"Srđan Vuletić"});
        movie27.setActors(new String[]{
                "Haris Sijarić/Fikret",
                "Svetozar Cvetković/Ramiz",
                "Kemal Ćebo/Tiki",
                "Zana Marjanović/Sara",
                "Aleksandar Seksan/Cico",
                "Admir Glamočak/Klupa"
        });
        movie27.setSynopsis("A group of friends in Bosnia decides to pursue quick wealth and excitement, getting tangled in the complex world of crime.");
        movie27.setTrailerUrl("https://www.youtube.com/watch?v=UieYbSsqQC0");
        movie27.setStatus("active");
        movie27.setCreatedAt(LocalDateTime.now());
        movie27.setUpdatedAt(LocalDateTime.now());
        movie27.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie27);

        MovieEntity movie28 = new MovieEntity();
        movie28.setTitle("Parasite");
        movie28.setLanguage("Korean");
        movie28.setDirector("Bong Joon-ho");
        movie28.setPgRating("R");
        movie28.setDurationInMinutes(132);
        movie28.setWriters(new String[]{"Bong Joon-ho", "Han Jin-won"});
        movie28.setActors(new String[]{
                "Song Kang-ho/Ki-taek",
                "Lee Sun-kyun/Mr. Park",
                "Cho Yeo-jeong/Yeon-kyo",
                "Choi Woo-shik/Ki-woo",
                "Park So-dam/Ki-jung",
                "Jang Hye-jin/Chung-sook"
        });
        movie28.setSynopsis("A poor family schemes to become employed by a wealthy family by infiltrating their household.");
        movie28.setTrailerUrl("https://www.youtube.com/watch?v=5xH0HfJHsaY");
        movie28.setStatus("active");
        movie28.setCreatedAt(LocalDateTime.now());
        movie28.setUpdatedAt(LocalDateTime.now());
        movie28.setGenreEntities(List.of(crudGenreRepository.findByName("Thriller"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie28);

        MovieEntity movie29 = new MovieEntity();
        movie29.setTitle("Spirited Away");
        movie29.setLanguage("Japanese");
        movie29.setDirector("Hayao Miyazaki");
        movie29.setPgRating("PG");
        movie29.setDurationInMinutes(125);
        movie29.setWriters(new String[]{"Hayao Miyazaki"});
        movie29.setActors(new String[]{
                "Rumi Hiiragi/Chihiro",
                "Miyu Irino/Haku",
                "Mari Natsuki/Yubaba",
                "Takashi Naito/Father",
                "Yasuko Sawaguchi/Mother",
                "Tatsuya Gashuin/Kamaji"
        });
        movie29.setSynopsis("A young girl becomes trapped in a strange world of spirits and must find her way out.");
        movie29.setTrailerUrl("https://www.youtube.com/watch?v=ByXuk9QqQkk");
        movie29.setStatus("active");
        movie29.setCreatedAt(LocalDateTime.now());
        movie29.setUpdatedAt(LocalDateTime.now());
        movie29.setGenreEntities(List.of(crudGenreRepository.findByName("Sci-Fi"), crudGenreRepository.findByName("Adventure")));
        crudMovieRepository.save(movie29);

        MovieEntity movie30 = new MovieEntity();
        movie30.setTitle("The Pianist");
        movie30.setLanguage("English");
        movie30.setDirector("Roman Polanski");
        movie30.setPgRating("R");
        movie30.setDurationInMinutes(150);
        movie30.setWriters(new String[]{"Ronald Harwood"});
        movie30.setActors(new String[]{
                "Adrien Brody/Władysław",
                "Thomas Kretschmann/Wilm",
                "Frank Finlay/Father",
                "Maureen Lipman/Mother",
                "Emilia Fox/Dorota",
                "Ed Stoppard/Henryk"
        });
        movie30.setSynopsis("A Polish Jewish musician struggles to survive the destruction of the Warsaw ghetto in World War II.");
        movie30.setTrailerUrl("https://www.youtube.com/watch?v=u_jE7-6Uv7E");
        movie30.setStatus("active");
        movie30.setCreatedAt(LocalDateTime.now());
        movie30.setUpdatedAt(LocalDateTime.now());
        movie30.setGenreEntities(List.of(crudGenreRepository.findByName("Action"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie30);

        MovieEntity movie31 = new MovieEntity();
        movie31.setTitle("Coco");
        movie31.setLanguage("English");
        movie31.setDirector("Lee Unkrich");
        movie31.setPgRating("PG");
        movie31.setDurationInMinutes(105);
        movie31.setWriters(new String[]{"Adrian Molina", "Matthew Aldrich"});
        movie31.setActors(new String[]{
                "Anthony Gonzalez/Miguel",
                "Gael Garcia Bernal/Hector",
                "Benjamin Bratt/Ernesto",
                "Alanna Ubach/Mama",
                "Renee Victor/Abuelita",
                "Ana Ofelia Murguia/Imelda"
        });
        movie31.setSynopsis("A boy accidentally travels to the Land of the Dead to seek help to restore his family's love of music.");
        movie31.setTrailerUrl("https://www.youtube.com/watch?v=Ga6RYejo6Hk");
        movie31.setStatus("active");
        movie31.setCreatedAt(LocalDateTime.now());
        movie31.setUpdatedAt(LocalDateTime.now());
        movie31.setGenreEntities(List.of(crudGenreRepository.findByName("Action")));
        crudMovieRepository.save(movie31);

        MovieEntity movie32 = new MovieEntity();
        movie32.setTitle("The Grand Budapest Hotel");
        movie32.setLanguage("English");
        movie32.setDirector("Wes Anderson");
        movie32.setPgRating("R");
        movie32.setDurationInMinutes(99);
        movie32.setWriters(new String[]{"Wes Anderson"});
        movie32.setActors(new String[]{
                "Ralph Fiennes/Monsieur",
                "F. Murray Abraham/Zero",
                "Mathieu Amalric/Serge",
                "Adrien Brody/Dmitri",
                "Willem Dafoe/J.G.",
                "Saoirse Ronan/Agatha"
        });
        movie32.setSynopsis("A writer encounters the owner of an aging hotel and hears his tale of adventure.");
        movie32.setTrailerUrl("https://www.youtube.com/watch?v=1Fg5iWmQjwk");
        movie32.setStatus("active");
        movie32.setCreatedAt(LocalDateTime.now());
        movie32.setUpdatedAt(LocalDateTime.now());
        movie32.setGenreEntities(List.of(crudGenreRepository.findByName("Comedy"), crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie32);

        MovieEntity movie33 = new MovieEntity();
        movie33.setTitle("Whiplash");
        movie33.setLanguage("English");
        movie33.setDirector("Damien Chazelle");
        movie33.setPgRating("R");
        movie33.setDurationInMinutes(106);
        movie33.setWriters(new String[]{"Damien Chazelle"});
        movie33.setActors(new String[]{
                "Miles Teller/Andrew",
                "J.K. Simmons/Fletcher",
                "Paul Reiser/Jim",
                "Melissa Benoist/Nicole",
                "Austin Stowell/Ryan",
                "Nate Lang/Carl"
        });
        movie33.setSynopsis("A young drummer's ambitious pursuit of perfection brings him face to face with a demanding instructor.");
        movie33.setTrailerUrl("https://www.youtube.com/watch?v=7d_jQycdQGo");
        movie33.setStatus("active");
        movie33.setCreatedAt(LocalDateTime.now());
        movie33.setUpdatedAt(LocalDateTime.now());
        movie33.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Sci-Fi")));
        crudMovieRepository.save(movie33);

        MovieEntity movie34 = new MovieEntity();
        movie34.setTitle("The Intouchables");
        movie34.setLanguage("French");
        movie34.setDirector("Olivier Nakache, Eric Toledano");
        movie34.setPgRating("R");
        movie34.setDurationInMinutes(112);
        movie34.setWriters(new String[]{"Olivier Nakache", "Eric Toledano"});
        movie34.setActors(new String[]{
                "François Cluzet/Philippe",
                "Omar Sy/Driss",
                "Anne Le Ny/Yvonne",
                "Audrey Fleurot/Magalie",
                "Alba Gaïa Bellugi/Elisa",
                "Cyril Mendy/Adama"
        });
        movie34.setSynopsis("After a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.");
        movie34.setTrailerUrl("https://www.youtube.com/watch?v=34WIbmXkewU");
        movie34.setStatus("active");
        movie34.setCreatedAt(LocalDateTime.now());
        movie34.setUpdatedAt(LocalDateTime.now());
        movie34.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Comedy")));
        crudMovieRepository.save(movie34);

        MovieEntity movie35 = new MovieEntity();
        movie35.setTitle("Mad Max: Fury Road");
        movie35.setLanguage("English");
        movie35.setDirector("George Miller");
        movie35.setPgRating("R");
        movie35.setDurationInMinutes(120);
        movie35.setWriters(new String[]{"George Miller", "Brendan McCarthy", "Nico Lathouris"});
        movie35.setActors(new String[]{
                "Tom Hardy/Max",
                "Charlize Theron/Furiosa",
                "Nicholas Hoult/Nux",
                "Hugh Keays/Immortan",
                "Josh Helman/Slit",
                "Nathan Jones/Rictus"
        });
        movie35.setSynopsis("In a post-apocalyptic wasteland, a woman rebels against a tyrant in search of her homeland.");
        movie35.setTrailerUrl("https://www.youtube.com/watch?v=hEJnMQG9ev8");
        movie35.setStatus("active");
        movie35.setCreatedAt(LocalDateTime.now());
        movie35.setUpdatedAt(LocalDateTime.now());
        movie35.setGenreEntities(List.of(crudGenreRepository.findByName("Action"), crudGenreRepository.findByName("Adventure")));
        crudMovieRepository.save(movie35);

        MovieEntity movie36 = new MovieEntity();
        movie36.setTitle("Inside Out");
        movie36.setLanguage("English");
        movie36.setDirector("Pete Docter");
        movie36.setPgRating("PG");
        movie36.setDurationInMinutes(95);
        movie36.setWriters(new String[]{"Pete Docter", "Meg LeFauve", "Josh Cooley"});
        movie36.setActors(new String[]{
                "Amy Poehler/Joy",
                "Phyllis Smith/Sadness",
                "Richard Kind/Bing Bong",
                "Bill Hader/Fear",
                "Lewis Black/Anger",
                "Mindy Kaling/Disgust"
        });
        movie36.setSynopsis("After young Riley is uprooted from her Midwest life, her emotions struggle to adjust.");
        movie36.setTrailerUrl("https://www.youtube.com/watch?v=yRUAzGQ3nSY");
        movie36.setStatus("active");
        movie36.setCreatedAt(LocalDateTime.now());
        movie36.setUpdatedAt(LocalDateTime.now());
        movie36.setGenreEntities(List.of(crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie36);

        MovieEntity movie37 = new MovieEntity();
        movie37.setTitle("The Revenant");
        movie37.setLanguage("English");
        movie37.setDirector("Alejandro González Iñárritu");
        movie37.setPgRating("R");
        movie37.setDurationInMinutes(156);
        movie37.setWriters(new String[]{"Mark L. Smith", "Alejandro González Iñárritu"});
        movie37.setActors(new String[]{
                "Leonardo DiCaprio/Hugh",
                "Tom Hardy/Fitzgerald",
                "Domhnall Gleeson/Captain",
                "Will Poulter/Bridger",
                "Forrest Goodluck/Hawk",
                "Paul Anderson/Anderson"
        });
        movie37.setSynopsis("A frontiersman fights for survival after being left for dead by his hunting team.");
        movie37.setTrailerUrl("https://www.youtube.com/watch?v=LoebZZ8K5N0");
        movie37.setStatus("active");
        movie37.setCreatedAt(LocalDateTime.now());
        movie37.setUpdatedAt(LocalDateTime.now());
        movie37.setGenreEntities(List.of(crudGenreRepository.findByName("Action"), crudGenreRepository.findByName("Adventure")));
        crudMovieRepository.save(movie37);

        MovieEntity movie38 = new MovieEntity();
        movie38.setTitle("Her");
        movie38.setLanguage("English");
        movie38.setDirector("Spike Jonze");
        movie38.setPgRating("R");
        movie38.setDurationInMinutes(126);
        movie38.setWriters(new String[]{"Spike Jonze"});
        movie38.setActors(new String[]{
                "Joaquin Phoenix/Theodore",
                "Scarlett Johansson/Samantha",
                "Amy Adams/Amy",
                "Rooney Mara/Catherine",
                "Olivia Wilde/Blind",
                "Chris Pratt/Paul"
        });
        movie38.setSynopsis("In a near future, a lonely writer develops an unlikely relationship with an operating system.");
        movie38.setTrailerUrl("https://www.youtube.com/watch?v=ne6p6MfLBxc");
        movie38.setStatus("active");
        movie38.setCreatedAt(LocalDateTime.now());
        movie38.setUpdatedAt(LocalDateTime.now());
        movie38.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie38);

        MovieEntity movie39 = new MovieEntity();
        movie39.setTitle("La La Land");
        movie39.setLanguage("English");
        movie39.setDirector("Damien Chazelle");
        movie39.setPgRating("PG-13");
        movie39.setDurationInMinutes(128);
        movie39.setWriters(new String[]{"Damien Chazelle"});
        movie39.setActors(new String[]{
                "Ryan Gosling/Sebastian",
                "Emma Stone/Mia",
                "John Legend/Keith",
                "Rosemarie DeWitt/Laura",
                "J.K. Simmons/Bill",
                "Amiee Conn/Lisa"
        });
        movie39.setSynopsis("While navigating their careers in Los Angeles, a pianist and an actress fall in love.");
        movie39.setTrailerUrl("https://www.youtube.com/watch?v=0pdqf4P9MB8");
        movie39.setStatus("active");
        movie39.setCreatedAt(LocalDateTime.now());
        movie39.setUpdatedAt(LocalDateTime.now());
        movie39.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Thriller")));
        crudMovieRepository.save(movie39);

        MovieEntity movie40 = new MovieEntity();
        movie40.setTitle("Moonlight");
        movie40.setLanguage("English");
        movie40.setDirector("Barry Jenkins");
        movie40.setPgRating("R");
        movie40.setDurationInMinutes(111);
        movie40.setWriters(new String[]{"Barry Jenkins", "Tarell Alvin McCraney"});
        movie40.setActors(new String[]{
                "Trevante Rhodes/Chiron",
                "Ashton Sanders/Teen",
                "Alex R. Hibbert/Little",
                "Naomie Harris/Paula",
                "Janelle Monáe/Teresa",
                "Mahershala Ali/Juan"
        });
        movie40.setSynopsis("A young African-American man grapples with his identity and sexuality while growing up.");
        movie40.setTrailerUrl("https://www.youtube.com/watch?v=9NJj12tJzqc");
        movie40.setStatus("active");
        movie40.setCreatedAt(LocalDateTime.now());
        movie40.setUpdatedAt(LocalDateTime.now());
        movie40.setGenreEntities(List.of(crudGenreRepository.findByName("Drama")));
        crudMovieRepository.save(movie40);

        // seed venue table
        VenueEntity venue1 = new VenueEntity();
        venue1.setName("Cineplexx Sarajevo");
        venue1.setStreet("Zmaja od Bosne");
        venue1.setStreetNumber(String.valueOf(4));
        venue1.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue1.setPhone("+38733-123-456");
        venue1.setCreatedAt(LocalDateTime.now());
        venue1.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue1);

        PhotoEntity cineplexxSarajevo = new PhotoEntity();
        cineplexxSarajevo.setEntityType("venue");
        cineplexxSarajevo.setRefEntityId(crudVenueRepository.findByName("Cineplexx Sarajevo").getId());
        cineplexxSarajevo.setUrl("https://s3proxygw.cineplexx.at/pimcore-bosnia-prod/assets/_default_upload_bucket/gr2.jpg");
        crudPhotoRepository.save(cineplexxSarajevo);

        HallEntity cineplexxSarajevoHall = new HallEntity();
        cineplexxSarajevoHall.setName("Hall 1");
        cineplexxSarajevoHall.setVenueEntity(crudVenueRepository.findByName("Cineplexx Sarajevo"));
        cineplexxSarajevoHall.setCreatedAt(LocalDateTime.now());
        cineplexxSarajevoHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(cineplexxSarajevoHall);

        VenueEntity venue2 = new VenueEntity();
        venue2.setName("Cinema City Sarajevo");
        venue2.setStreet("Maršala Tita");
        venue2.setStreetNumber(String.valueOf(10));
        venue2.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue2.setPhone("+38733-321-654");
        venue2.setCreatedAt(LocalDateTime.now());
        venue2.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue2);

        PhotoEntity cinemaCitySarajevo = new PhotoEntity();
        cinemaCitySarajevo.setEntityType("venue");
        cinemaCitySarajevo.setRefEntityId(crudVenueRepository.findByName("Cinema City Sarajevo").getId());
        cinemaCitySarajevo.setUrl("https://www.sarajevo.ba/media/article/1178/original/kina36719.jpg");
        crudPhotoRepository.save(cinemaCitySarajevo);

        HallEntity cinemaCitySarajevoHall = new HallEntity();
        cinemaCitySarajevoHall.setName("Hall 1");
        cinemaCitySarajevoHall.setVenueEntity(crudVenueRepository.findByName("Cinema City Sarajevo"));
        cinemaCitySarajevoHall.setCreatedAt(LocalDateTime.now());
        cinemaCitySarajevoHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(cinemaCitySarajevoHall);

        VenueEntity venue3 = new VenueEntity();
        venue3.setName("Mostar Cinema");
        venue3.setStreet("Kralja Tomislava");
        venue3.setStreetNumber(String.valueOf(25));
        venue3.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue3.setPhone("+38736-456-789");
        venue3.setCreatedAt(LocalDateTime.now());
        venue3.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue3);

        PhotoEntity mostarCinema = new PhotoEntity();
        mostarCinema.setEntityType("venue");
        mostarCinema.setRefEntityId(crudVenueRepository.findByName("Mostar Cinema").getId());
        mostarCinema.setUrl("https://www.hercegbosna.org/slike_upload/20120322/velicina1/herceg_bosna201203221035220.jpg");
        crudPhotoRepository.save(mostarCinema);

        HallEntity mostarCinemaHall = new HallEntity();
        mostarCinemaHall.setName("Hall 1");
        mostarCinemaHall.setVenueEntity(crudVenueRepository.findByName("Mostar Cinema"));
        mostarCinemaHall.setCreatedAt(LocalDateTime.now());
        mostarCinemaHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(mostarCinemaHall);

        VenueEntity venue4 = new VenueEntity();
        venue4.setName("Sarajevo Film Center");
        venue4.setStreet("Obala Kulina Bana");
        venue4.setStreetNumber(String.valueOf(66));
        venue4.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue4.setPhone("+38733-654-321");
        venue4.setCreatedAt(LocalDateTime.now());
        venue4.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue4);

        PhotoEntity sarajevoFilmCenter = new PhotoEntity();
        sarajevoFilmCenter.setEntityType("venue");
        sarajevoFilmCenter.setRefEntityId(crudVenueRepository.findByName("Sarajevo Film Center").getId());
        sarajevoFilmCenter.setUrl("https://img.etimg.com/thumb/msid-104359417,width-650,height-488,imgsize-54656,resizemode-75/cinema-halls1.jpg");
        crudPhotoRepository.save(sarajevoFilmCenter);

        HallEntity sarajevoFilmCenterHall = new HallEntity();
        sarajevoFilmCenterHall.setName("Hall 1");
        sarajevoFilmCenterHall.setVenueEntity(crudVenueRepository.findByName("Sarajevo Film Center"));
        sarajevoFilmCenterHall.setCreatedAt(LocalDateTime.now());
        sarajevoFilmCenterHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(sarajevoFilmCenterHall);

        VenueEntity venue5 = new VenueEntity();
        venue5.setName("Multiplex Mostar");
        venue5.setStreet("Stjepana Radića");
        venue5.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue5.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue5.setPhone("+38736-987-654");
        venue5.setCreatedAt(LocalDateTime.now());
        venue5.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue5);

        PhotoEntity multiplexMostar = new PhotoEntity();
        multiplexMostar.setEntityType("venue");
        multiplexMostar.setRefEntityId(crudVenueRepository.findByName("Multiplex Mostar").getId());
        multiplexMostar.setUrl("https://jubitz.com/wp-content/uploads/2023/12/Edited.-Inside-Cinema.png");
        crudPhotoRepository.save(multiplexMostar);

        HallEntity multiplexMostarHall = new HallEntity();
        multiplexMostarHall.setName("Hall 1");
        multiplexMostarHall.setVenueEntity(crudVenueRepository.findByName("Multiplex Mostar"));
        multiplexMostarHall.setCreatedAt(LocalDateTime.now());
        multiplexMostarHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(multiplexMostarHall);

        VenueEntity venue6 = new VenueEntity();
        venue6.setName("CineStar Sarajevo");
        venue6.setStreet("Džemala Bijedića");
        venue6.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue6.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue6.setPhone("+38733-111-222");
        venue6.setCreatedAt(LocalDateTime.now());
        venue6.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue6);

        PhotoEntity cinestarSarajevo = new PhotoEntity();
        cinestarSarajevo.setEntityType("venue");
        cinestarSarajevo.setRefEntityId(crudVenueRepository.findByName("CineStar Sarajevo").getId());
        cinestarSarajevo.setUrl("https://n1info.ba/wp-content/uploads/2020/10/cinestar-1-318893-1024x576.jpeg");
        crudPhotoRepository.save(cinestarSarajevo);

        HallEntity cinestarSarajevoHall = new HallEntity();
        cinestarSarajevoHall.setName("Hall 1");
        cinestarSarajevoHall.setVenueEntity(crudVenueRepository.findByName("CineStar Sarajevo"));
        cinestarSarajevoHall.setCreatedAt(LocalDateTime.now());
        cinestarSarajevoHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(cinestarSarajevoHall);

        VenueEntity venue7 = new VenueEntity();
        venue7.setName("Cinema City Mostar");
        venue7.setStreet("Kardinala Stepinca");
        venue7.setStreetNumber(String.valueOf(Math.round(Math.random() * 50)));
        venue7.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue7.setPhone("+38736-333-444");
        venue7.setCreatedAt(LocalDateTime.now());
        venue7.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue7);

        PhotoEntity cinemaCityMostar = new PhotoEntity();
        cinemaCityMostar.setEntityType("venue");
        cinemaCityMostar.setRefEntityId(crudVenueRepository.findByName("Cinema City Mostar").getId());
        cinemaCityMostar.setUrl("https://m.smedata.sk/api-media/media/image/spectator/3/88/8852203/8852203_1200x.jpg?rev=3");
        crudPhotoRepository.save(cinemaCityMostar);

        HallEntity cinemaCityMostarHall = new HallEntity();
        cinemaCityMostarHall.setName("Hall 1");
        cinemaCityMostarHall.setVenueEntity(crudVenueRepository.findByName("Cinema City Mostar"));
        cinemaCityMostarHall.setCreatedAt(LocalDateTime.now());
        cinemaCityMostarHall.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(cinemaCityMostarHall);


        // Seed projections
        String[] startTimes = {"14:00", "16:00", "18:00", "20:00", "22:15", "23:50"};

        // Active Projection for "Avatar" at Cineplexx Sarajevo
        ProjectionEntity projection1 = new ProjectionEntity();
        projection1.setMovieEntity(crudMovieRepository.findByTitle("Avatar"));
        projection1.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        projection1.setStartDate(LocalDate.now().minusDays(5));
        projection1.setEndDate(LocalDate.now().plusDays(5));
        projection1.setStartTime(new String[]{startTimes[0], startTimes[1]});
        projection1.setStatus("active");
        projection1.setCreatedAt(LocalDateTime.now());
        projection1.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection1);

        // Active Projection for "Inception" at Cinema City Sarajevo
        ProjectionEntity projection2 = new ProjectionEntity();
        projection2.setMovieEntity(crudMovieRepository.findByTitle("Inception"));
        projection2.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        projection2.setStartDate(LocalDate.now().minusDays(4));
        projection2.setEndDate(LocalDate.now().plusDays(6));
        projection2.setStartTime(new String[]{startTimes[1], startTimes[2]});
        projection2.setStatus("active");
        projection2.setCreatedAt(LocalDateTime.now());
        projection2.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection2);

        // Active Projection for "Titanic" at Mostar Cinema
        ProjectionEntity projection3 = new ProjectionEntity();
        projection3.setMovieEntity(crudMovieRepository.findByTitle("Titanic"));
        projection3.setHallEntity(crudVenueRepository.findByName("Mostar Cinema").getHallEntities().get(0));
        projection3.setStartDate(LocalDate.now().minusDays(3));
        projection3.setEndDate(LocalDate.now().plusDays(7));
        projection3.setStartTime(new String[]{startTimes[2], startTimes[3]});
        projection3.setStatus("active");
        projection3.setCreatedAt(LocalDateTime.now());
        projection3.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection3);

        // Active Projection for "The Dark Knight" at Sarajevo Film Center
        ProjectionEntity projection4 = new ProjectionEntity();
        projection4.setMovieEntity(crudMovieRepository.findByTitle("The Dark Knight"));
        projection4.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        projection4.setStartDate(LocalDate.now().minusDays(2));
        projection4.setEndDate(LocalDate.now().plusDays(8));
        projection4.setStartTime(new String[]{startTimes[3], startTimes[4]});
        projection4.setStatus("active");
        projection4.setCreatedAt(LocalDateTime.now());
        projection4.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection4);

        // Active Projection for "The Matrix" at Multiplex Mostar
        ProjectionEntity projection5 = new ProjectionEntity();
        projection5.setMovieEntity(crudMovieRepository.findByTitle("The Matrix"));
        projection5.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        projection5.setStartDate(LocalDate.now().minusDays(1));
        projection5.setEndDate(LocalDate.now().plusDays(9));
        projection5.setStartTime(new String[]{startTimes[4], startTimes[5]});
        projection5.setStatus("active");
        projection5.setCreatedAt(LocalDateTime.now());
        projection5.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection5);

        // Active Projection for "Interstellar" at CineStar Sarajevo
        ProjectionEntity interstellarProjection = new ProjectionEntity();
        interstellarProjection.setMovieEntity(crudMovieRepository.findByTitle("Interstellar"));
        interstellarProjection.setHallEntity(crudVenueRepository.findByName("CineStar Sarajevo").getHallEntities().get(0));
        interstellarProjection.setStartDate(LocalDate.now().minusDays(5));
        interstellarProjection.setEndDate(LocalDate.now().plusDays(5));
        interstellarProjection.setStartTime(new String[]{startTimes[0], startTimes[2]}); // Using 14:00, 18:00
        interstellarProjection.setStatus("active");
        interstellarProjection.setCreatedAt(LocalDateTime.now());
        interstellarProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(interstellarProjection);

        // Active Projection for "Gladiator" at Multiplex Mostar
        ProjectionEntity gladiatorProjection = new ProjectionEntity();
        gladiatorProjection.setMovieEntity(crudMovieRepository.findByTitle("Gladiator"));
        gladiatorProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        gladiatorProjection.setStartDate(LocalDate.now().minusDays(4));
        gladiatorProjection.setEndDate(LocalDate.now().plusDays(6));
        gladiatorProjection.setStartTime(new String[]{startTimes[1], startTimes[3]}); // Using 16:00, 20:00
        gladiatorProjection.setStatus("active");
        gladiatorProjection.setCreatedAt(LocalDateTime.now());
        gladiatorProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(gladiatorProjection);

        // Active Projection for "The Godfather" at Cinema City Mostar
        ProjectionEntity godfatherProjection = new ProjectionEntity();
        godfatherProjection.setMovieEntity(crudMovieRepository.findByTitle("The Godfather"));
        godfatherProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Mostar").getHallEntities().get(0));
        godfatherProjection.setStartDate(LocalDate.now().minusDays(3));
        godfatherProjection.setEndDate(LocalDate.now().plusDays(7));
        godfatherProjection.setStartTime(new String[]{startTimes[2], startTimes[4]}); // Using 18:00, 22:15
        godfatherProjection.setStatus("active");
        godfatherProjection.setCreatedAt(LocalDateTime.now());
        godfatherProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(godfatherProjection);

        // Active Projection for "Pulp Fiction" at Sarajevo Film Center
        ProjectionEntity pulpFictionProjection = new ProjectionEntity();
        pulpFictionProjection.setMovieEntity(crudMovieRepository.findByTitle("Pulp Fiction"));
        pulpFictionProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        pulpFictionProjection.setStartDate(LocalDate.now().minusDays(2));
        pulpFictionProjection.setEndDate(LocalDate.now().plusDays(8));
        pulpFictionProjection.setStartTime(new String[]{startTimes[3], startTimes[5]}); // Using 20:00, 23:50
        pulpFictionProjection.setStatus("active");
        pulpFictionProjection.setCreatedAt(LocalDateTime.now());
        pulpFictionProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(pulpFictionProjection);

        // Active Projection for "Schindler's List" at Cineplexx Sarajevo
        ProjectionEntity schindlersListProjection = new ProjectionEntity();
        schindlersListProjection.setMovieEntity(crudMovieRepository.findByTitle("Schindler's List"));
        schindlersListProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        schindlersListProjection.setStartDate(LocalDate.now().minusDays(1));
        schindlersListProjection.setEndDate(LocalDate.now().plusDays(9));
        schindlersListProjection.setStartTime(new String[]{startTimes[4], startTimes[0]}); // Using 22:15, 14:00
        schindlersListProjection.setStatus("active");
        schindlersListProjection.setCreatedAt(LocalDateTime.now());
        schindlersListProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(schindlersListProjection);

        // Active Projection for "Fight Club" at Cinema City Sarajevo
        ProjectionEntity fightClubProjection = new ProjectionEntity();
        fightClubProjection.setMovieEntity(crudMovieRepository.findByTitle("Fight Club"));
        fightClubProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        fightClubProjection.setStartDate(LocalDate.now().minusDays(5));
        fightClubProjection.setEndDate(LocalDate.now().plusDays(5));
        fightClubProjection.setStartTime(new String[]{startTimes[0], startTimes[1]});
        fightClubProjection.setStatus("active");
        fightClubProjection.setCreatedAt(LocalDateTime.now());
        fightClubProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(fightClubProjection);

        // Active Projection for "Forrest Gump" at Mostar Cinema
        ProjectionEntity forrestGumpProjection = new ProjectionEntity();
        forrestGumpProjection.setMovieEntity(crudMovieRepository.findByTitle("Forrest Gump"));
        forrestGumpProjection.setHallEntity(crudVenueRepository.findByName("Mostar Cinema").getHallEntities().get(0));
        forrestGumpProjection.setStartDate(LocalDate.now().minusDays(4));
        forrestGumpProjection.setEndDate(LocalDate.now().plusDays(6));
        forrestGumpProjection.setStartTime(new String[]{startTimes[1], startTimes[2]});
        forrestGumpProjection.setStatus("active");
        forrestGumpProjection.setCreatedAt(LocalDateTime.now());
        forrestGumpProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(forrestGumpProjection);

        // Active Projection for "The Lion King" at Cineplexx Sarajevo
        ProjectionEntity lionKingProjection = new ProjectionEntity();
        lionKingProjection.setMovieEntity(crudMovieRepository.findByTitle("The Lion King"));
        lionKingProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        lionKingProjection.setStartDate(LocalDate.now().minusDays(3));
        lionKingProjection.setEndDate(LocalDate.now().plusDays(7));
        lionKingProjection.setStartTime(new String[]{startTimes[2], startTimes[3]});
        lionKingProjection.setStatus("active");
        lionKingProjection.setCreatedAt(LocalDateTime.now());
        lionKingProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(lionKingProjection);

        // Active Projection for "Saving Private Ryan" at Sarajevo Film Center
        ProjectionEntity savingPrivateRyanProjection = new ProjectionEntity();
        savingPrivateRyanProjection.setMovieEntity(crudMovieRepository.findByTitle("Saving Private Ryan"));
        savingPrivateRyanProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        savingPrivateRyanProjection.setStartDate(LocalDate.now().minusDays(2));
        savingPrivateRyanProjection.setEndDate(LocalDate.now().plusDays(8));
        savingPrivateRyanProjection.setStartTime(new String[]{startTimes[3], startTimes[4]});
        savingPrivateRyanProjection.setStatus("active");
        savingPrivateRyanProjection.setCreatedAt(LocalDateTime.now());
        savingPrivateRyanProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(savingPrivateRyanProjection);

        // Active Projection for "The Shawshank Redemption" at Multiplex Mostar
        ProjectionEntity shawshankRedemptionProjection = new ProjectionEntity();
        shawshankRedemptionProjection.setMovieEntity(crudMovieRepository.findByTitle("The Shawshank Redemption"));
        shawshankRedemptionProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        shawshankRedemptionProjection.setStartDate(LocalDate.now().minusDays(1));
        shawshankRedemptionProjection.setEndDate(LocalDate.now().plusDays(9));
        shawshankRedemptionProjection.setStartTime(new String[]{startTimes[4], startTimes[5]});
        shawshankRedemptionProjection.setStatus("active");
        shawshankRedemptionProjection.setCreatedAt(LocalDateTime.now());
        shawshankRedemptionProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(shawshankRedemptionProjection);

        // Active Projection for "The Green Mile" at CineStar Sarajevo
        ProjectionEntity greenMileProjection = new ProjectionEntity();
        greenMileProjection.setMovieEntity(crudMovieRepository.findByTitle("The Green Mile"));
        greenMileProjection.setHallEntity(crudVenueRepository.findByName("CineStar Sarajevo").getHallEntities().get(0));
        greenMileProjection.setStartDate(LocalDate.now().minusDays(5));
        greenMileProjection.setEndDate(LocalDate.now().plusDays(5));
        greenMileProjection.setStartTime(new String[]{startTimes[0], startTimes[2]});
        greenMileProjection.setStatus("active");
        greenMileProjection.setCreatedAt(LocalDateTime.now());
        greenMileProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(greenMileProjection);

        // Active Projection for "The Avengers" at Multiplex Mostar
        ProjectionEntity avengersProjection = new ProjectionEntity();
        avengersProjection.setMovieEntity(crudMovieRepository.findByTitle("The Avengers"));
        avengersProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        avengersProjection.setStartDate(LocalDate.now().minusDays(4));
        avengersProjection.setEndDate(LocalDate.now().plusDays(6));
        avengersProjection.setStartTime(new String[]{startTimes[1], startTimes[3]});
        avengersProjection.setStatus("active");
        avengersProjection.setCreatedAt(LocalDateTime.now());
        avengersProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(avengersProjection);

        // Active Projection for "Jurassic Park" at Cinema City Mostar
        ProjectionEntity jurassicParkProjection = new ProjectionEntity();
        jurassicParkProjection.setMovieEntity(crudMovieRepository.findByTitle("Jurassic Park"));
        jurassicParkProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Mostar").getHallEntities().get(0));
        jurassicParkProjection.setStartDate(LocalDate.now().minusDays(3));
        jurassicParkProjection.setEndDate(LocalDate.now().plusDays(7));
        jurassicParkProjection.setStartTime(new String[]{startTimes[2], startTimes[4]});
        jurassicParkProjection.setStatus("active");
        jurassicParkProjection.setCreatedAt(LocalDateTime.now());
        jurassicParkProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(jurassicParkProjection);

        // Active Projection for "The Silence of the Lambs" at Sarajevo Film Center
        ProjectionEntity silenceOfLambsProjection = new ProjectionEntity();
        silenceOfLambsProjection.setMovieEntity(crudMovieRepository.findByTitle("The Silence of the Lambs"));
        silenceOfLambsProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        silenceOfLambsProjection.setStartDate(LocalDate.now().minusDays(2));
        silenceOfLambsProjection.setEndDate(LocalDate.now().plusDays(8));
        silenceOfLambsProjection.setStartTime(new String[]{startTimes[3], startTimes[5]});
        silenceOfLambsProjection.setStatus("active");
        silenceOfLambsProjection.setCreatedAt(LocalDateTime.now());
        silenceOfLambsProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(silenceOfLambsProjection);

        // Active Projection for "The Lord of the Rings: The Fellowship of the Ring" at Cineplexx Sarajevo
        ProjectionEntity lotrFellowshipProjection = new ProjectionEntity();
        lotrFellowshipProjection.setMovieEntity(crudMovieRepository.findByTitle("The Lord of the Rings: The Fellowship of the Ring"));
        lotrFellowshipProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        lotrFellowshipProjection.setStartDate(LocalDate.now().minusDays(1));
        lotrFellowshipProjection.setEndDate(LocalDate.now().plusDays(9));
        lotrFellowshipProjection.setStartTime(new String[]{startTimes[4], startTimes[0]});
        lotrFellowshipProjection.setStatus("active");
        lotrFellowshipProjection.setCreatedAt(LocalDateTime.now());
        lotrFellowshipProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(lotrFellowshipProjection);

        // Upcoming Projection for "No Man's Land" at Cinema City Sarajevo
        ProjectionEntity noMansLandProjection = new ProjectionEntity();
        noMansLandProjection.setMovieEntity(crudMovieRepository.findByTitle("No Man's Land"));
        noMansLandProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        noMansLandProjection.setStartDate(LocalDate.now().plusDays(5));
        noMansLandProjection.setEndDate(LocalDate.now().plusDays(10));
        noMansLandProjection.setStartTime(new String[]{startTimes[0], startTimes[1]});
        noMansLandProjection.setStatus("upcoming");
        noMansLandProjection.setCreatedAt(LocalDateTime.now());
        noMansLandProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(noMansLandProjection);

        // Upcoming Projection for "Grbavica" at Mostar Cinema
        ProjectionEntity grbavicaProjection = new ProjectionEntity();
        grbavicaProjection.setMovieEntity(crudMovieRepository.findByTitle("Grbavica"));
        grbavicaProjection.setHallEntity(crudVenueRepository.findByName("Mostar Cinema").getHallEntities().get(0));
        grbavicaProjection.setStartDate(LocalDate.now().plusDays(4));
        grbavicaProjection.setEndDate(LocalDate.now().plusDays(14));
        grbavicaProjection.setStartTime(new String[]{startTimes[1], startTimes[2]});
        grbavicaProjection.setStatus("upcoming");
        grbavicaProjection.setCreatedAt(LocalDateTime.now());
        grbavicaProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(grbavicaProjection);

        // Upcoming Projection for "Quo Vadis, Aida?" at Cineplexx Sarajevo
        ProjectionEntity quoVadisAidaProjection = new ProjectionEntity();
        quoVadisAidaProjection.setMovieEntity(crudMovieRepository.findByTitle("Quo Vadis, Aida?"));
        quoVadisAidaProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        quoVadisAidaProjection.setStartDate(LocalDate.now().plusDays(3));
        quoVadisAidaProjection.setEndDate(LocalDate.now().plusDays(13));
        quoVadisAidaProjection.setStartTime(new String[]{startTimes[2], startTimes[3]});
        quoVadisAidaProjection.setStatus("upcoming");
        quoVadisAidaProjection.setCreatedAt(LocalDateTime.now());
        quoVadisAidaProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(quoVadisAidaProjection);

        // Upcoming Projection for "The Perfect Circle" at Sarajevo Film Center
        ProjectionEntity perfectCircleProjection = new ProjectionEntity();
        perfectCircleProjection.setMovieEntity(crudMovieRepository.findByTitle("The Perfect Circle"));
        perfectCircleProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        perfectCircleProjection.setStartDate(LocalDate.now().plusDays(2));
        perfectCircleProjection.setEndDate(LocalDate.now().plusDays(12));
        perfectCircleProjection.setStartTime(new String[]{startTimes[3], startTimes[5]});
        perfectCircleProjection.setStatus("upcoming");
        perfectCircleProjection.setCreatedAt(LocalDateTime.now());
        perfectCircleProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(perfectCircleProjection);

        // Upcoming Projection for "An Episode in the Life of an Iron Picker" at Multiplex Mostar
        ProjectionEntity ironPickerProjection = new ProjectionEntity();
        ironPickerProjection.setMovieEntity(crudMovieRepository.findByTitle("An Episode in the Life of an Iron Picker"));
        ironPickerProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        ironPickerProjection.setStartDate(LocalDate.now().plusDays(1));
        ironPickerProjection.setEndDate(LocalDate.now().plusDays(11));
        ironPickerProjection.setStartTime(new String[]{startTimes[4], startTimes[0]});
        ironPickerProjection.setStatus("upcoming");
        ironPickerProjection.setCreatedAt(LocalDateTime.now());
        ironPickerProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(ironPickerProjection);

        // Upcoming Projection for "It's hard to be nice" at CineStar Sarajevo
        ProjectionEntity hardToBeNiceProjection = new ProjectionEntity();
        hardToBeNiceProjection.setMovieEntity(crudMovieRepository.findByTitle("It's hard to be nice"));
        hardToBeNiceProjection.setHallEntity(crudVenueRepository.findByName("CineStar Sarajevo").getHallEntities().get(0));
        hardToBeNiceProjection.setStartDate(LocalDate.now().plusDays(15));
        hardToBeNiceProjection.setEndDate(LocalDate.now().plusDays(25));
        hardToBeNiceProjection.setStartTime(new String[]{startTimes[0], startTimes[2]});
        hardToBeNiceProjection.setStatus("upcoming");
        hardToBeNiceProjection.setCreatedAt(LocalDateTime.now());
        hardToBeNiceProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(hardToBeNiceProjection);

        // Upcoming Projection for "Summer in Golden Valley" at Multiplex Mostar
        ProjectionEntity summerGoldenValleyProjection = new ProjectionEntity();
        summerGoldenValleyProjection.setMovieEntity(crudMovieRepository.findByTitle("Summer in Golden Valley"));
        summerGoldenValleyProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        summerGoldenValleyProjection.setStartDate(LocalDate.now().plusDays(9));
        summerGoldenValleyProjection.setEndDate(LocalDate.now().plusDays(19));
        summerGoldenValleyProjection.setStartTime(new String[]{startTimes[1], startTimes[3]});
        summerGoldenValleyProjection.setStatus("upcoming");
        summerGoldenValleyProjection.setCreatedAt(LocalDateTime.now());
        summerGoldenValleyProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(summerGoldenValleyProjection);

        // Upcoming Projection for "Parasite" at Cinema City Sarajevo
        ProjectionEntity parasiteProjection = new ProjectionEntity();
        parasiteProjection.setMovieEntity(crudMovieRepository.findByTitle("Parasite"));
        parasiteProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        parasiteProjection.setStartDate(LocalDate.now().plusDays(8));
        parasiteProjection.setEndDate(LocalDate.now().plusDays(18));
        parasiteProjection.setStartTime(new String[]{startTimes[2], startTimes[4]});
        parasiteProjection.setStatus("upcoming");
        parasiteProjection.setCreatedAt(LocalDateTime.now());
        parasiteProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(parasiteProjection);

        // Upcoming Projection for "Spirited Away" at Sarajevo Film Center
        ProjectionEntity spiritedAwayProjection = new ProjectionEntity();
        spiritedAwayProjection.setMovieEntity(crudMovieRepository.findByTitle("Spirited Away"));
        spiritedAwayProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        spiritedAwayProjection.setStartDate(LocalDate.now().plusDays(2));
        spiritedAwayProjection.setEndDate(LocalDate.now().plusDays(12));
        spiritedAwayProjection.setStartTime(new String[]{startTimes[3], startTimes[5]});
        spiritedAwayProjection.setStatus("upcoming");
        spiritedAwayProjection.setCreatedAt(LocalDateTime.now());
        spiritedAwayProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(spiritedAwayProjection);

        // Upcoming Projection for "The Pianist" at Cineplexx Sarajevo
        ProjectionEntity pianistProjection = new ProjectionEntity();
        pianistProjection.setMovieEntity(crudMovieRepository.findByTitle("The Pianist"));
        pianistProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        pianistProjection.setStartDate(LocalDate.now().plusDays(15));
        pianistProjection.setEndDate(LocalDate.now().plusDays(25));
        pianistProjection.setStartTime(new String[]{startTimes[4], startTimes[0]});
        pianistProjection.setStatus("upcoming");
        pianistProjection.setCreatedAt(LocalDateTime.now());
        pianistProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(pianistProjection);

        ProjectionEntity cocoProjection = new ProjectionEntity();
        cocoProjection.setMovieEntity(crudMovieRepository.findByTitle("Coco"));
        cocoProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        cocoProjection.setStartDate(LocalDate.now().plusDays(10));
        cocoProjection.setEndDate(LocalDate.now().plusDays(20));
        cocoProjection.setStartTime(new String[]{startTimes[0], startTimes[2], startTimes[4]});
        cocoProjection.setStatus("upcoming");
        cocoProjection.setCreatedAt(LocalDateTime.now());
        cocoProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(cocoProjection);

        // Upcoming Projection for "The Grand Budapest Hotel" at Sarajevo Film Center
        ProjectionEntity grandBudapestProjection = new ProjectionEntity();
        grandBudapestProjection.setMovieEntity(crudMovieRepository.findByTitle("The Grand Budapest Hotel"));
        grandBudapestProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        grandBudapestProjection.setStartDate(LocalDate.now().plusDays(12));
        grandBudapestProjection.setEndDate(LocalDate.now().plusDays(22));
        grandBudapestProjection.setStartTime(new String[]{startTimes[1], startTimes[3]});
        grandBudapestProjection.setStatus("upcoming");
        grandBudapestProjection.setCreatedAt(LocalDateTime.now());
        grandBudapestProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(grandBudapestProjection);

        // Upcoming Projection for "Whiplash" at Multiplex Mostar
        ProjectionEntity whiplashProjection = new ProjectionEntity();
        whiplashProjection.setMovieEntity(crudMovieRepository.findByTitle("Whiplash"));
        whiplashProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        whiplashProjection.setStartDate(LocalDate.now().plusDays(8));
        whiplashProjection.setEndDate(LocalDate.now().plusDays(18));
        whiplashProjection.setStartTime(new String[]{startTimes[2], startTimes[4], startTimes[5]});
        whiplashProjection.setStatus("upcoming");
        whiplashProjection.setCreatedAt(LocalDateTime.now());
        whiplashProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(whiplashProjection);

        // Upcoming Projection for "The Intouchables" at Cinema City Sarajevo
        ProjectionEntity intouchablesProjection = new ProjectionEntity();
        intouchablesProjection.setMovieEntity(crudMovieRepository.findByTitle("The Intouchables"));
        intouchablesProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        intouchablesProjection.setStartDate(LocalDate.now().plusDays(7));
        intouchablesProjection.setEndDate(LocalDate.now().plusDays(17));
        intouchablesProjection.setStartTime(new String[]{startTimes[3], startTimes[5]});
        intouchablesProjection.setStatus("upcoming");
        intouchablesProjection.setCreatedAt(LocalDateTime.now());
        intouchablesProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(intouchablesProjection);

        // Upcoming Projection for "Mad Max: Fury Road" at CineStar Sarajevo
        ProjectionEntity madMaxProjection = new ProjectionEntity();
        madMaxProjection.setMovieEntity(crudMovieRepository.findByTitle("Mad Max: Fury Road"));
        madMaxProjection.setHallEntity(crudVenueRepository.findByName("CineStar Sarajevo").getHallEntities().get(0));
        madMaxProjection.setStartDate(LocalDate.now().plusDays(6));
        madMaxProjection.setEndDate(LocalDate.now().plusDays(16));
        madMaxProjection.setStartTime(new String[]{startTimes[0], startTimes[2], startTimes[4]});
        madMaxProjection.setStatus("upcoming");
        madMaxProjection.setCreatedAt(LocalDateTime.now());
        madMaxProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(madMaxProjection);

        // Upcoming Projection for "Inside Out" at Cineplexx Sarajevo
        ProjectionEntity insideOutProjection = new ProjectionEntity();
        insideOutProjection.setMovieEntity(crudMovieRepository.findByTitle("Inside Out"));
        insideOutProjection.setHallEntity(crudVenueRepository.findByName("Cineplexx Sarajevo").getHallEntities().get(0));
        insideOutProjection.setStartDate(LocalDate.now().plusDays(4));
        insideOutProjection.setEndDate(LocalDate.now().plusDays(14));
        insideOutProjection.setStartTime(new String[]{startTimes[0], startTimes[2], startTimes[4]});
        insideOutProjection.setStatus("upcoming");
        insideOutProjection.setCreatedAt(LocalDateTime.now());
        insideOutProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(insideOutProjection);

        // Upcoming Projection for "The Revenant" at Sarajevo Film Center
        ProjectionEntity revenantProjection = new ProjectionEntity();
        revenantProjection.setMovieEntity(crudMovieRepository.findByTitle("The Revenant"));
        revenantProjection.setHallEntity(crudVenueRepository.findByName("Sarajevo Film Center").getHallEntities().get(0));
        revenantProjection.setStartDate(LocalDate.now().plusDays(6));
        revenantProjection.setEndDate(LocalDate.now().plusDays(16));
        revenantProjection.setStartTime(new String[]{startTimes[1], startTimes[3]});
        revenantProjection.setStatus("upcoming");
        revenantProjection.setCreatedAt(LocalDateTime.now());
        revenantProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(revenantProjection);

        // Upcoming Projection for "Her" at Cinema City Sarajevo
        ProjectionEntity herProjection = new ProjectionEntity();
        herProjection.setMovieEntity(crudMovieRepository.findByTitle("Her"));
        herProjection.setHallEntity(crudVenueRepository.findByName("Cinema City Sarajevo").getHallEntities().get(0));
        herProjection.setStartDate(LocalDate.now().plusDays(8));
        herProjection.setEndDate(LocalDate.now().plusDays(18));
        herProjection.setStartTime(new String[]{startTimes[2], startTimes[4], startTimes[5]});
        herProjection.setStatus("upcoming");
        herProjection.setCreatedAt(LocalDateTime.now());
        herProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(herProjection);

        // Upcoming Projection for "La La Land" at Multiplex Mostar
        ProjectionEntity laLaLandProjection = new ProjectionEntity();
        laLaLandProjection.setMovieEntity(crudMovieRepository.findByTitle("La La Land"));
        laLaLandProjection.setHallEntity(crudVenueRepository.findByName("Multiplex Mostar").getHallEntities().get(0));
        laLaLandProjection.setStartDate(LocalDate.now().plusDays(7));
        laLaLandProjection.setEndDate(LocalDate.now().plusDays(17));
        laLaLandProjection.setStartTime(new String[]{startTimes[3], startTimes[5]});
        laLaLandProjection.setStatus("upcoming");
        laLaLandProjection.setCreatedAt(LocalDateTime.now());
        laLaLandProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(laLaLandProjection);

        // Upcoming Projection for "Moonlight" at CineStar Sarajevo
        ProjectionEntity moonlightProjection = new ProjectionEntity();
        moonlightProjection.setMovieEntity(crudMovieRepository.findByTitle("Moonlight"));
        moonlightProjection.setHallEntity(crudVenueRepository.findByName("CineStar Sarajevo").getHallEntities().get(0));
        moonlightProjection.setStartDate(LocalDate.now().plusDays(9));
        moonlightProjection.setEndDate(LocalDate.now().plusDays(19));
        moonlightProjection.setStartTime(new String[]{startTimes[0], startTimes[2]});
        moonlightProjection.setStatus("upcoming");
        moonlightProjection.setCreatedAt(LocalDateTime.now());
        moonlightProjection.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(moonlightProjection);


        // seed photo table
        PhotoEntity photo1 = new PhotoEntity();
        photo1.setEntityType("movie");
        photo1.setRefEntityId(crudMovieRepository.findByTitle("Avatar").getId());
        photo1.setUrl("https://www.usmagazine.com/wp-content/uploads/2022/07/James-Cameron-Warns-Avatar-2-Is-3-Hours-Its-OK-Get-Up-Go-Pee.jpg?quality=86&strip=all");
        movie1.setCoverPhotoId(crudPhotoRepository.save(photo1).getId());
        crudMovieRepository.save(movie1);

        PhotoEntity photo2 = new PhotoEntity();
        photo2.setEntityType("movie");
        photo2.setRefEntityId(crudMovieRepository.findByTitle("Inception").getId());
        photo2.setUrl("https://nextbestpicture-com.b-cdn.net/wp-content/uploads/2024/04/Inception.jpg");
        movie2.setCoverPhotoId(crudPhotoRepository.save(photo2).getId());
        crudMovieRepository.save(movie2);

        PhotoEntity photo3 = new PhotoEntity();
        photo3.setEntityType("movie");
        photo3.setRefEntityId(crudMovieRepository.findByTitle("Titanic").getId());
        photo3.setUrl("https://i0.wp.com/awardswatch.com/wp-content/uploads/2023/02/Screenshot-2023-02-13-at-8.04.06-AM.png?resize=768%2C445&ssl=1");
        movie3.setCoverPhotoId(crudPhotoRepository.save(photo3).getId());
        crudMovieRepository.save(movie3);

        PhotoEntity photo4 = new PhotoEntity();
        photo4.setEntityType("movie");
        photo4.setRefEntityId(crudMovieRepository.findByTitle("The Dark Knight").getId());
        photo4.setUrl("https://www.prime1studio.com/on/demandware.static/-/Sites-p1s-master-catalog/default/dwe65eb6a7/images/HDMMDC-02/media/hdmmdc-02_a19.jpg");
        movie4.setCoverPhotoId(crudPhotoRepository.save(photo4).getId());
        crudMovieRepository.save(movie4);

        PhotoEntity photo5 = new PhotoEntity();
        photo5.setEntityType("movie");
        photo5.setRefEntityId(crudMovieRepository.findByTitle("The Matrix").getId());
        photo5.setUrl("https://storage.googleapis.com/pod_public/1300/106922.jpg");
        movie5.setCoverPhotoId(crudPhotoRepository.save(photo5).getId());
        crudMovieRepository.save(movie5);

        PhotoEntity photo6 = new PhotoEntity();
        photo6.setEntityType("movie");
        photo6.setRefEntityId(crudMovieRepository.findByTitle("Interstellar").getId());
        photo6.setUrl("https://www.seacoastonline.com/gcdn/authoring/2014/11/07/NPOH/ghows-SO-00f35d1e-def9-4ba8-9155-d936bed5f192-b9e187d3.jpeg?width=660&height=413&fit=crop&format=pjpg&auto=webp");
        movie6.setCoverPhotoId(crudPhotoRepository.save(photo6).getId());
        crudMovieRepository.save(movie6);

        PhotoEntity photo7 = new PhotoEntity();
        photo7.setEntityType("movie");
        photo7.setRefEntityId(crudMovieRepository.findByTitle("Gladiator").getId());
        photo7.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/0*TxZdqJf0MQ2mdL9p.jpg");
        movie7.setCoverPhotoId(crudPhotoRepository.save(photo7).getId());
        crudMovieRepository.save(movie7);

        PhotoEntity photo8 = new PhotoEntity();
        photo8.setEntityType("movie");
        photo8.setRefEntityId(crudMovieRepository.findByTitle("The Godfather").getId());
        photo8.setUrl("https://jerseymanmagazine.com/wp-content/uploads/2022/04/The-Godfather-e1648766305891-900x600.jpg");
        movie8.setCoverPhotoId(crudPhotoRepository.save(photo8).getId());
        crudMovieRepository.save(movie8);

        PhotoEntity photo9 = new PhotoEntity();
        photo9.setEntityType("movie");
        photo9.setRefEntityId(crudMovieRepository.findByTitle("Pulp Fiction").getId());
        photo9.setUrl("https://media.wired.com/photos/59323e2a52d99d6b984dd3f4/master/w_1920,c_limit/Pulp-Fiction.jpg");
        movie9.setCoverPhotoId(crudPhotoRepository.save(photo9).getId());
        crudMovieRepository.save(movie9);

        PhotoEntity photo10 = new PhotoEntity();
        photo10.setEntityType("movie");
        photo10.setRefEntityId(crudMovieRepository.findByTitle("Schindler's List").getId());
        photo10.setUrl("https://goldendiscs.ie/cdn/shop/products/817sLmprCSL._AC_SY445.jpg?v=1690429826");
        movie10.setCoverPhotoId(crudPhotoRepository.save(photo10).getId());
        crudMovieRepository.save(movie10);

        PhotoEntity photo11 = new PhotoEntity();
        photo11.setEntityType("movie");
        photo11.setRefEntityId(crudMovieRepository.findByTitle("Fight Club").getId());
        photo11.setUrl("https://media.newyorker.com/photos/5dbafcc91b4a6700085a7a9b/master/w_1920,c_limit/Baker-FightClub.jpg");
        movie11.setCoverPhotoId(crudPhotoRepository.save(photo11).getId());
        crudMovieRepository.save(movie11);

        PhotoEntity photo12 = new PhotoEntity();
        photo12.setEntityType("movie");
        photo12.setRefEntityId(crudMovieRepository.findByTitle("Forrest Gump").getId());
        photo12.setUrl("https://ntvb.tmsimg.com/assets/p15829_v_h8_aw.jpg?w=1280&h=720");
        movie12.setCoverPhotoId(crudPhotoRepository.save(photo12).getId());
        crudMovieRepository.save(movie12);

        PhotoEntity photo13 = new PhotoEntity();
        photo13.setEntityType("movie");
        photo13.setRefEntityId(crudMovieRepository.findByTitle("The Lion King").getId());
        photo13.setUrl("https://m.media-amazon.com/images/I/51UjjVDiEDL._SX342_SY445_.jpg");
        movie13.setCoverPhotoId(crudPhotoRepository.save(photo13).getId());
        crudMovieRepository.save(movie13);

        PhotoEntity photo14 = new PhotoEntity();
        photo14.setEntityType("movie");
        photo14.setRefEntityId(crudMovieRepository.findByTitle("Saving Private Ryan").getId());
        photo14.setUrl("https://www.soundandvision.com/images/styles/600_wide/public/110722_director%27s_intent_saving_pvt_ryan_promo.png");
        movie14.setCoverPhotoId(crudPhotoRepository.save(photo14).getId());
        crudMovieRepository.save(movie14);

        PhotoEntity photo15 = new PhotoEntity();
        photo15.setEntityType("movie");
        photo15.setRefEntityId(crudMovieRepository.findByTitle("The Shawshank Redemption").getId());
        photo15.setUrl("https://static1.srcdn.com/wordpress/wp-content/uploads/2023/12/red-and-andy-in-shawshank-redemption.jpg?q=70&fit=crop&w=1140&h=&dpr=1");
        movie15.setCoverPhotoId(crudPhotoRepository.save(photo15).getId());
        crudMovieRepository.save(movie15);

        PhotoEntity photo16 = new PhotoEntity();
        photo16.setEntityType("movie");
        photo16.setRefEntityId(crudMovieRepository.findByTitle("The Green Mile").getId());
        photo16.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/1*oVTrOMrJv9FwP2WecfEG-g.png");
        movie16.setCoverPhotoId(crudPhotoRepository.save(photo16).getId());
        crudMovieRepository.save(movie16);

        PhotoEntity photo17 = new PhotoEntity();
        photo17.setEntityType("movie");
        photo17.setRefEntityId(crudMovieRepository.findByTitle("The Avengers").getId());
        photo17.setUrl("https://cdn.marvel.com/content/1x/avengersendgame_lob_crd_05.jpg");
        movie17.setCoverPhotoId(crudPhotoRepository.save(photo17).getId());
        crudMovieRepository.save(movie17);

        PhotoEntity photo18 = new PhotoEntity();
        photo18.setEntityType("movie");
        photo18.setRefEntityId(crudMovieRepository.findByTitle("Jurassic Park").getId());
        photo18.setUrl("https://m.media-amazon.com/images/I/61W7iD5+XKL._SX342_SY445_.jpg");
        movie18.setCoverPhotoId(crudPhotoRepository.save(photo18).getId());
        crudMovieRepository.save(movie18);

        PhotoEntity photo19 = new PhotoEntity();
        photo19.setEntityType("movie");
        photo19.setRefEntityId(crudMovieRepository.findByTitle("The Silence of the Lambs").getId());
        photo19.setUrl("https://images.bauerhosting.com/legacy/media/6026/a037/4ef6/302f/a23a/5c22/sotl-3.jpg?ar=16%3A9&fit=crop&crop=top&auto=format&w=992&q=80");
        movie19.setCoverPhotoId(crudPhotoRepository.save(photo19).getId());
        crudMovieRepository.save(movie19);

        PhotoEntity photo20 = new PhotoEntity();
        photo20.setEntityType("movie");
        photo20.setRefEntityId(crudMovieRepository.findByTitle("The Lord of the Rings: The Fellowship of the Ring").getId());
        photo20.setUrl("https://images.cdn.prd.api.discomax.com/2023/05/05/be192083-0dfc-3df5-bba2-67725ab6b0fc.jpeg?f=jpg&q=75&w=1280&w=1200");
        movie20.setCoverPhotoId(crudPhotoRepository.save(photo20).getId());
        crudMovieRepository.save(movie20);

        PhotoEntity photo21 = new PhotoEntity();
        photo21.setEntityType("movie");
        photo21.setRefEntityId(crudMovieRepository.findByTitle("No Man's Land").getId());
        photo21.setUrl("https://upload.wikimedia.org/wikipedia/sr/3/3a/Nicija_zemlja.jpg");
        movie21.setCoverPhotoId(crudPhotoRepository.save(photo21).getId());
        crudMovieRepository.save(movie21);

        PhotoEntity photo22 = new PhotoEntity();
        photo22.setEntityType("movie");
        photo22.setRefEntityId(crudMovieRepository.findByTitle("Grbavica").getId());
        photo22.setUrl("https://upload.wikimedia.org/wikipedia/bs/5/56/Grbavica_poster.jpg");
        movie22.setCoverPhotoId(crudPhotoRepository.save(photo22).getId());
        crudMovieRepository.save(movie22);

        PhotoEntity photo23 = new PhotoEntity();
        photo23.setEntityType("movie");
        photo23.setRefEntityId(crudMovieRepository.findByTitle("Quo Vadis, Aida?").getId());
        photo23.setUrl("https://pad.mymovies.it/filmclub/2020/07/230/locandina.jpg");
        movie23.setCoverPhotoId(crudPhotoRepository.save(photo23).getId());
        crudMovieRepository.save(movie23);

        PhotoEntity photo24 = new PhotoEntity();
        photo24.setEntityType("movie");
        photo24.setRefEntityId(crudMovieRepository.findByTitle("The Perfect Circle").getId());
        photo24.setUrl("https://avaz.ba/media/2022/07/19/1867142/adis-film.jpg");
        movie24.setCoverPhotoId(crudPhotoRepository.save(photo24).getId());
        crudMovieRepository.save(movie24);

        PhotoEntity photo25 = new PhotoEntity();
        photo25.setEntityType("movie");
        photo25.setRefEntityId(crudMovieRepository.findByTitle("An Episode in the Life of an Iron Picker").getId());
        photo25.setUrl("https://upload.wikimedia.org/wikipedia/bs/e/e6/Epizoda_u_%C5%BEivotu_bera%C4%8Da_%C5%BEeljeza.jpg");
        movie25.setCoverPhotoId(crudPhotoRepository.save(photo25).getId());
        crudMovieRepository.save(movie25);

        PhotoEntity photo26 = new PhotoEntity();
        photo26.setEntityType("movie");
        photo26.setRefEntityId(crudMovieRepository.findByTitle("It's hard to be nice").getId());
        photo26.setUrl("https://3.bp.blogspot.com/-9Qc3LcjZ3wA/VFJGtny62cI/AAAAAAAAB5c/Wu7DXAW37AE/s1600/tesko-je-biti-fin.jpg");
        movie26.setCoverPhotoId(crudPhotoRepository.save(photo26).getId());
        crudMovieRepository.save(movie26);

        PhotoEntity photo27 = new PhotoEntity();
        photo27.setEntityType("movie");
        photo27.setRefEntityId(crudMovieRepository.findByTitle("Summer in Golden Valley").getId());
        photo27.setUrl("https://www.filmofil.ba/images/content/photo-gallery/Ljeto_u_zlatnoj_dolini_21674920786.jpg");
        movie27.setCoverPhotoId(crudPhotoRepository.save(photo27).getId());
        crudMovieRepository.save(movie27);

        PhotoEntity photo28 = new PhotoEntity();
        photo28.setEntityType("movie");
        photo28.setRefEntityId(crudMovieRepository.findByTitle("Parasite").getId());
        photo28.setUrl("https://media.newyorker.com/photos/5da4a5c756dcd400082a63ba/master/w_1920,c_limit/Brody-Parasite.jpg");
        movie28.setCoverPhotoId(crudPhotoRepository.save(photo28).getId());
        crudMovieRepository.save(movie28);

        PhotoEntity photo29 = new PhotoEntity();
        photo29.setEntityType("movie");
        photo29.setRefEntityId(crudMovieRepository.findByTitle("Spirited Away").getId());
        photo29.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/1*64vCXnQ-7nWnEY4FgpCuiQ.jpeg");
        movie29.setCoverPhotoId(crudPhotoRepository.save(photo29).getId());
        crudMovieRepository.save(movie29);

        PhotoEntity photo30 = new PhotoEntity();
        photo30.setEntityType("movie");
        photo30.setRefEntityId(crudMovieRepository.findByTitle("The Pianist").getId());
        photo30.setUrl("https://filmforum.org/do-not-enter-or-modify-or-erase/client-uploads/_1000w/THE_PIANIST_slideshow_1.png");
        movie30.setCoverPhotoId(crudPhotoRepository.save(photo30).getId());
        crudMovieRepository.save(movie30);

        PhotoEntity photo31 = new PhotoEntity();
        photo31.setEntityType("movie");
        photo31.setRefEntityId(crudMovieRepository.findByTitle("Coco").getId());
        photo31.setUrl("https://positionpapers.ie/wp-content/uploads/2017/11/coco-disney-pixar.jpg");
        movie31.setCoverPhotoId(crudPhotoRepository.save(photo31).getId());
        crudMovieRepository.save(movie31);

        PhotoEntity photo32 = new PhotoEntity();
        photo32.setEntityType("movie");
        photo32.setRefEntityId(crudMovieRepository.findByTitle("The Grand Budapest Hotel").getId());
        photo32.setUrl("https://www.rogerebert.com/wp-content/uploads/2024/07/The-Grand-Budapest-Hotel.jpg");
        movie32.setCoverPhotoId(crudPhotoRepository.save(photo32).getId());
        crudMovieRepository.save(movie32);

        PhotoEntity photo33 = new PhotoEntity();
        photo33.setEntityType("movie");
        photo33.setRefEntityId(crudMovieRepository.findByTitle("Whiplash").getId());
        photo33.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/0*b92aG7V12Hgq23MD.jpg");
        movie33.setCoverPhotoId(crudPhotoRepository.save(photo33).getId());
        crudMovieRepository.save(movie33);

        PhotoEntity photo34 = new PhotoEntity();
        photo34.setEntityType("movie");
        photo34.setRefEntityId(crudMovieRepository.findByTitle("The Intouchables").getId());
        photo34.setUrl("https://theobjectivestandard.com/wp-content/uploads/2013/05/Review-The-Intouchables-2048x1216.jpeg");
        movie34.setCoverPhotoId(crudPhotoRepository.save(photo34).getId());
        crudMovieRepository.save(movie34);

        PhotoEntity photo35 = new PhotoEntity();
        photo35.setEntityType("movie");
        photo35.setRefEntityId(crudMovieRepository.findByTitle("Mad Max: Fury Road").getId());
        photo35.setUrl("https://media.newyorker.com/photos/59096da5ebe912338a3769c0/master/w_1920,c_limit/Brody-Mad-Max-2.jpg");
        movie35.setCoverPhotoId(crudPhotoRepository.save(photo35).getId());
        crudMovieRepository.save(movie35);

        PhotoEntity photo36 = new PhotoEntity();
        photo36.setEntityType("movie");
        photo36.setRefEntityId(crudMovieRepository.findByTitle("Inside Out").getId());
        photo36.setUrl("https://img-cdn.inc.com/image/upload/f_webp,c_fit,w_828,q_auto/images/panoramic/inside-out-2-inc_541857_pgzat8.jpg");
        movie36.setCoverPhotoId(crudPhotoRepository.save(photo36).getId());
        crudMovieRepository.save(movie36);

        PhotoEntity photo37 = new PhotoEntity();
        photo37.setEntityType("movie");
        photo37.setRefEntityId(crudMovieRepository.findByTitle("The Revenant").getId());
        photo37.setUrl("https://d13jj08vfqimqg.cloudfront.net/uploads/article/header_marquee/4553/large_the-revenant.jpg");
        movie37.setCoverPhotoId(crudPhotoRepository.save(photo37).getId());
        crudMovieRepository.save(movie37);

        PhotoEntity photo38 = new PhotoEntity();
        photo38.setEntityType("movie");
        photo38.setRefEntityId(crudMovieRepository.findByTitle("Her").getId());
        photo38.setUrl("https://miro.medium.com/v2/resize:fit:3840/format:webp/1*UblqmBcsbdhQovCRmmRJbQ.jpeg");
        movie38.setCoverPhotoId(crudPhotoRepository.save(photo38).getId());
        crudMovieRepository.save(movie38);

        PhotoEntity photo39 = new PhotoEntity();
        photo39.setEntityType("movie");
        photo39.setRefEntityId(crudMovieRepository.findByTitle("La La Land").getId());
        photo39.setUrl("https://www.eskimotv.net/imgsizer/img/cover-images/la-la-land-2016-cover-image.jpg?h=466&m=crop&v=Xtmy1g&w=994&s=b%27QhcmPsdxusCS075UDdaGqdmY6DQ%27");
        movie39.setCoverPhotoId(crudPhotoRepository.save(photo39).getId());
        crudMovieRepository.save(movie39);

        PhotoEntity photo40 = new PhotoEntity();
        photo40.setEntityType("movie");
        photo40.setRefEntityId(crudMovieRepository.findByTitle("Moonlight").getId());
        photo40.setUrl("https://miro.medium.com/v2/resize:fit:828/format:webp/1*rP32E2FlEUvD55sn-9D8Dg.png");
        movie40.setCoverPhotoId(crudPhotoRepository.save(photo40).getId());
        crudMovieRepository.save(movie40);

        System.out.println("Database seeded successfully");
    }
//    }
}
