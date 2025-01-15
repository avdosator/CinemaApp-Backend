-- Table: ticket_price

CREATE TABLE ticket_price (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    seat_type VARCHAR(20) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_seat_type UNIQUE (seat_type)
);

-- Insert current prices

INSERT INTO ticket_price (seat_type, price) VALUES
('regular', 7.00),
('vip', 10.00),
('love', 24.00);