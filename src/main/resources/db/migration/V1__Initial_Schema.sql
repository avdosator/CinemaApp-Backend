
-- Creating tables for CinemaApp with DEFAULT CURRENT_TIMESTAMP

-- Create City table
CREATE TABLE city (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    postal_code INT,
    country VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Venue table
CREATE TABLE venue (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    street VARCHAR(255),
    street_number VARCHAR(10),
    city_id UUID,
    phone VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_venue_city FOREIGN KEY (city_id) REFERENCES city(id)
);

-- Create Hall table
CREATE TABLE hall (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    venue_id UUID,
    total_seats INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_hall_venue FOREIGN KEY (venue_id) REFERENCES venue(id)
);

-- Create User table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(320),
    password_hash VARCHAR(255),
    phone VARCHAR(30),
    city_id UUID,
    role VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_city FOREIGN KEY (city_id) REFERENCES city(id)
);

-- Create Genre table
CREATE TABLE genre (
    id UUID PRIMARY KEY,
    name VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Movie table
CREATE TABLE movie (
    id UUID PRIMARY KEY,
    title VARCHAR(100),
    language VARCHAR(50),
    director VARCHAR(100),
    pg_rating VARCHAR(15),
    duration INT,
    writers TEXT[],
    actors TEXT[],
    imdb_rating DECIMAL(3,1),
    rotten_tomatoes_rating DECIMAL(3,1),
    synopsis TEXT,
    trailer_url TEXT,
    cover_photo_id UUID,
    status VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Projection table
CREATE TABLE projection (
    id UUID PRIMARY KEY,
    hall_id UUID,
    movie_id UUID,
    start_date DATE,
    end_date DATE,
    start_time VARCHAR[],
    available_seats INT,
    status VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_projection_hall FOREIGN KEY (hall_id) REFERENCES hall(id),
    CONSTRAINT fk_projection_movie FOREIGN KEY (movie_id) REFERENCES movie(id)
);

-- Create Payment table
CREATE TABLE payment (
    id UUID PRIMARY KEY,
    user_id UUID,
    amount DECIMAL(10,2),
    method VARCHAR(50),
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payment_users FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create Ticket table
CREATE TABLE ticket (
    id UUID PRIMARY KEY,
    payment_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ticket_payment FOREIGN KEY (payment_id) REFERENCES payment(id)
);

-- Create Reservation table
CREATE TABLE reservation (
    id UUID PRIMARY KEY,
    user_id UUID,
    status VARCHAR(20),
    total_price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservation_users FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create Seat table
CREATE TABLE seat (
    id UUID PRIMARY KEY,
    number VARCHAR(10),
    hall_id UUID,
    type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_seat_hall FOREIGN KEY (hall_id) REFERENCES hall(id)
);

-- Create Seat_Reservation table
CREATE TABLE seat_reservation (
    id UUID PRIMARY KEY,
    projection_id UUID,
    seat_id UUID,
    user_id UUID,
    ticket_id UUID,
    reservation_id UUID,
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
    id UUID PRIMARY KEY,
    url TEXT,
    entity_id UUID,
    entity_type VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Movie_Genre linking table
CREATE TABLE movie_genre (
    movie_id UUID,
    genre_id UUID,
    PRIMARY KEY (movie_id, genre_id),
    CONSTRAINT fk_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES genre(id)
);
