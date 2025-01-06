-- Step 1: Remove the available_seats column from the projection table
ALTER TABLE projection
    DROP COLUMN available_seats;
-- Step 2: Update the seat_reservation table to connect with projection_instance instead of projection
-- Drop the existing foreign key constraint (constraint name placeholder used since H2 generates names dynamically)
ALTER TABLE seat_reservation
    DROP CONSTRAINT IF EXISTS fk8oargobgjhtg4o7djpcl835oi;
-- Add the new column for projection_instance
ALTER TABLE seat_reservation
    ADD COLUMN projection_instance_id VARCHAR(36); -- UUID replaced with VARCHAR(36)
-- Add the new foreign key constraint for projection_instance
ALTER TABLE seat_reservation
    ADD CONSTRAINT fk_seat_reservation_projection_instance
        FOREIGN KEY (projection_instance_id) REFERENCES projection_instance(id);
-- Step 3: Remove the old projection_id column from seat_reservation
ALTER TABLE seat_reservation
    DROP COLUMN projection_id;