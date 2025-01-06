-- Add ProjectionInstance table
CREATE TABLE projection_instance (
    id UUID PRIMARY KEY,
    projection_id UUID NOT NULL,
    date DATE NOT NULL, -- Specific date of the projection instance
    time VARCHAR(10) NOT NULL, -- Specific time of the projection instance
    seats_status JSONB NOT NULL DEFAULT '{}', -- JSON column for managing seat availability
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_projection_instance_projection FOREIGN KEY (projection_id) REFERENCES projection(id),
    CONSTRAINT uq_projection_instance UNIQUE (projection_id, date, time) -- Ensure no duplicate instances for the same projection, date, and time
);

-- Add unique constraint to the email column in the users table
ALTER TABLE users
ADD CONSTRAINT uq_users_email UNIQUE (email);