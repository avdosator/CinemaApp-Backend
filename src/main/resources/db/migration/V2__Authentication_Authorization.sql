-- Table: refresh_token

CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    token_hash CHAR(128) NOT NULL,
    user_id UUID NOT NULL,
    expiration TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refresh_token_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE INDEX idx_refresh_token_users ON refresh_token(user_id);
CREATE UNIQUE INDEX idx_refresh_token_token_hash ON refresh_token(token_hash);

CREATE TABLE password_reset (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    reset_code VARCHAR(4) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_password_reset_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);