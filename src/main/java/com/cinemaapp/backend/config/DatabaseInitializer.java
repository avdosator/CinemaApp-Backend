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
        movie27.setTitle("Summer in Golden Walley");
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
        movie29.setGenreEntities(List.of(crudGenreRepository.findByName("Fantasy"), crudGenreRepository.findByName("Adventure")));
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
        movie30.setGenreEntities(List.of(crudGenreRepository.findByName("Biography"), crudGenreRepository.findByName("Drama")));
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
        movie31.setGenreEntities(List.of(crudGenreRepository.findByName("Animation"), crudGenreRepository.findByName("Family")));
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
        movie33.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Music")));
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
        movie34.setGenreEntities(List.of(crudGenreRepository.findByName("Biography"), crudGenreRepository.findByName("Comedy")));
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
        movie36.setGenreEntities(List.of(crudGenreRepository.findByName("Animation"), crudGenreRepository.findByName("Family")));
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
        movie38.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Romance")));
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
        movie39.setGenreEntities(List.of(crudGenreRepository.findByName("Drama"), crudGenreRepository.findByName("Musical")));
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

        HallEntity hall1 = new HallEntity();
        hall1.setName("Hall 1");
        hall1.setVenueEntity(crudVenueRepository.findByName("Cineplexx Sarajevo"));
        hall1.setCreatedAt(LocalDateTime.now());
        hall1.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall1);

        VenueEntity venue2 = new VenueEntity();
        venue2.setName("Cinema City Sarajevo");
        venue2.setStreet("Maršala Tita");
        venue2.setStreetNumber(String.valueOf(10));
        venue2.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue2.setPhone("+38733-321-654");
        venue2.setCreatedAt(LocalDateTime.now());
        venue2.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue2);

        HallEntity hall2 = new HallEntity();
        hall2.setName("Hall 1");
        hall2.setVenueEntity(crudVenueRepository.findByName("Cinema City Sarajevo"));
        hall2.setCreatedAt(LocalDateTime.now());
        hall2.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall2);

        VenueEntity venue3 = new VenueEntity();
        venue3.setName("Mostar Cinema");
        venue3.setStreet("Kralja Tomislava");
        venue3.setStreetNumber(String.valueOf(25));
        venue3.setCityEntity(crudCityRepository.findByName("Mostar"));
        venue3.setPhone("+38736-456-789");
        venue3.setCreatedAt(LocalDateTime.now());
        venue3.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue3);

        HallEntity hall3 = new HallEntity();
        hall3.setName("Hall 1");
        hall3.setVenueEntity(crudVenueRepository.findByName("Mostar Cinema"));
        hall3.setCreatedAt(LocalDateTime.now());
        hall3.setUpdatedAt(LocalDateTime.now());
        crudHallRepository.save(hall3);

        VenueEntity venue4 = new VenueEntity();
        venue4.setName("Sarajevo Film Center");
        venue4.setStreet("Obala Kulina Bana");
        venue4.setStreetNumber(String.valueOf(Math.round(66)));
        venue4.setCityEntity(crudCityRepository.findByName("Sarajevo"));
        venue4.setPhone("+38733-654-321");
        venue4.setCreatedAt(LocalDateTime.now());
        venue4.setUpdatedAt(LocalDateTime.now());
        crudVenueRepository.save(venue4);

        HallEntity hall4 = new HallEntity();
        hall4.setName("Hall 1");
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
        projection18.setStartTime(new String[]{startTimes[3], startTimes[5]});
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
        projection21.setStartTime(new String[]{startTimes[4], startTimes[5]});
        projection21.setStatus("active");
        projection21.setCreatedAt(LocalDateTime.now());
        projection21.setUpdatedAt(LocalDateTime.now());
        crudProjectionRepository.save(projection21);

        ProjectionEntity projection22 = new ProjectionEntity();
        projection22.setHallEntity(crudHallRepository.findByName("Hall 2"));
        projection22.setStartDate(LocalDate.now().plusDays(3));
        projection22.setEndDate(LocalDate.now().plusDays(12));
        projection22.setMovieEntity(crudMovieRepository.findByTitle("Jurassic Park"));
        projection22.setStartTime(new String[]{startTimes[0], startTimes[5]});
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
        photo3.setUrl("https://i0.wp.com/awardswatch.com/wp-content/uploads/2023/02/Screenshot-2023-02-13-at-8.04.06-AM.png?resize=768%2C445&ssl=1");
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
