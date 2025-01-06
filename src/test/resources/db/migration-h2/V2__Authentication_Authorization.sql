-- Table: refresh_token
CREATE TABLE refresh_token (
                               id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                               token_hash CHAR(128) NOT NULL,
                               user_id VARCHAR(36) NOT NULL, -- UUID replaced with VARCHAR(36)
                               expiration TIMESTAMP NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_refresh_token_users FOREIGN KEY (user_id) REFERENCES users(id)
);
-- Indexes for refresh_token
CREATE INDEX idx_refresh_token_users ON refresh_token(user_id);
CREATE UNIQUE INDEX idx_refresh_token_token_hash ON refresh_token(token_hash);
-- Table: password_reset
CREATE TABLE password_reset (
                                id VARCHAR(36) PRIMARY KEY, -- UUID replaced with VARCHAR(36)
                                user_id VARCHAR(36) NOT NULL, -- UUID replaced with VARCHAR(36)
                                reset_code VARCHAR(4) NOT NULL,
                                expiration_time TIMESTAMP NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);