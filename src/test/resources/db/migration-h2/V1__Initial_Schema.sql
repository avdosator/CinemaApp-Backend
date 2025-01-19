-- H2 Compatibility Adjustments
-- Default `CURRENT_TIMESTAMP` kept as is since it is supported in H2.
-- UUID replaced with `VARCHAR(36)` for simplicity.
-- TEXT[] and VARCHAR[] replaced with serialized `TEXT`.
-- PostgreSQL-specific features such as array types and certain constraints adjusted.
-- Create City table
CREATE TABLE city (
                      id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                      name VARCHAR(100),
                      postal_code INT,
                      country VARCHAR(50),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create Venue table
CREATE TABLE venue (
                       id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                       name VARCHAR(100),
                       street VARCHAR(255),
                       street_number VARCHAR(10),
                       city_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                       phone VARCHAR(30),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_venue_city FOREIGN KEY (city_id) REFERENCES city(id)
);
-- Create Hall table
CREATE TABLE hall (
                      id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                      name VARCHAR(100),
                      venue_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                      total_seats INT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT fk_hall_venue FOREIGN KEY (venue_id) REFERENCES venue(id)
);
-- Create User table
CREATE TABLE users (
                       id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(320),
                       password_hash VARCHAR(255),
                       phone VARCHAR(30),
                       city_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                       role VARCHAR(30),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_user_city FOREIGN KEY (city_id) REFERENCES city(id)
);
-- Create Genre table
CREATE TABLE genre (
                       id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                       name VARCHAR(30),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create Movie table
CREATE TABLE movie (
                       id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                       title VARCHAR(100),
                       language VARCHAR(50),
                       director VARCHAR(100),
                       pg_rating VARCHAR(15),
                       duration INT,
                       writers TEXT, -- Replacing TEXT[] with TEXT (serialized format)
                       actors TEXT, -- Replacing TEXT[] with TEXT (serialized format)
                       imdb_rating DECIMAL(3,1),
                       rotten_tomatoes_rating DECIMAL(3,1),
                       synopsis TEXT,
                       trailer_url TEXT,
                       cover_photo_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                       status VARCHAR(30),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create Projection table
CREATE TABLE projection (
                            id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                            hall_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                            movie_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                            start_date DATE,
                            end_date DATE,
                            start_time TEXT, -- Replacing VARCHAR[] with TEXT (serialized format)
                            available_seats INT,
                            status VARCHAR(30),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT fk_projection_hall FOREIGN KEY (hall_id) REFERENCES hall(id),
                            CONSTRAINT fk_projection_movie FOREIGN KEY (movie_id) REFERENCES movie(id)
);
-- Create Payment table
CREATE TABLE payment (
                         id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                         user_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                         amount DECIMAL(10,2),
                         method VARCHAR(50),
                         payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(30),
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_payment_users FOREIGN KEY (user_id) REFERENCES users(id)
);
-- Create Ticket table
CREATE TABLE ticket (
                        id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                        payment_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_ticket_payment FOREIGN KEY (payment_id) REFERENCES payment(id)
);
-- Create Reservation table
CREATE TABLE reservation (
                             id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                             user_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                             status VARCHAR(20),
                             total_price DECIMAL(10,2),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT fk_reservation_users FOREIGN KEY (user_id) REFERENCES users(id)
);
-- Create Seat table
CREATE TABLE seat (
                      id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                      number VARCHAR(10),
                      hall_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                      type VARCHAR(50),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT fk_seat_hall FOREIGN KEY (hall_id) REFERENCES hall(id)
);
-- Create Seat_Reservation table
CREATE TABLE seat_reservation (
                                  id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                                  projection_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                                  seat_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                                  user_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                                  ticket_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                                  reservation_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                                  status VARCHAR(20),
                                  reservation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT fk_seat_reservation_projection FOREIGN KEY (projection_id) REFERENCES projection(id),
                                  CONSTRAINT fk_seat_reservation_seat FOREIGN KEY (seat_id) REFERENCES seat(id),
                                  CONSTRAINT fk_seat_reservation_users FOREIGN KEY (user_id) REFERENCES users(id),
                                  CONSTRAINT fk_seat_reservation_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id),
                                  CONSTRAINT fk_seat_reservation_reservation FOREIGN KEY (reservation_id) REFERENCES reservation(id)
);
-- Create Photo table
CREATE TABLE photo (
                       id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                       url TEXT,
                       entity_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                       entity_type VARCHAR(50), -- Added length for consistency
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create Movie_Genre linking table
CREATE TABLE movie_genre (
                             movie_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                             genre_id VARCHAR(36), -- UUID replaced with VARCHAR(36)
                             PRIMARY KEY (movie_id, genre_id),
                             CONSTRAINT fk_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
                             CONSTRAINT fk_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES genre(id)
);