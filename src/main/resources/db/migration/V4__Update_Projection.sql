-- Step 1: Remove the available_seats column from the projection table
ALTER TABLE projection
DROP COLUMN available_seats;

-- Step 2: Update the seat_reservation table to connect with projection_instance instead of projection
ALTER TABLE seat_reservation
DROP CONSTRAINT fk8oargobgjhtg4o7djpcl835oi, -- Drop the existing foreign key constraint
ADD COLUMN projection_instance_id UUID,         -- Add the new column for projection_instance
ADD CONSTRAINT fk_seat_reservation_projection_instance
    FOREIGN KEY (projection_instance_id) REFERENCES projection_instance(id);

-- Step 3: Remove the old projection_id column from seat_reservation
ALTER TABLE seat_reservation
DROP COLUMN projection_id;