-- Migration script to modify projection_instance and movie tables

-- Remove the seats_status column from the projection_instance table
ALTER TABLE projection_instance
DROP COLUMN seats_status;

-- Change imdb_rating and rotten_tomatoes_rating columns in the movie table to VARCHAR
ALTER TABLE movie
ALTER COLUMN imdb_rating TYPE VARCHAR(10),
ALTER COLUMN rotten_tomatoes_rating TYPE VARCHAR(10);
