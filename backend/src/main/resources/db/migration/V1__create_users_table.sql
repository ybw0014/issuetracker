CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT PRIMARY KEY NOT NULL,
    username   VARCHAR(255)       NOT NULL,
    email      VARCHAR(255),
    role       VARCHAR(255)       NOT NULL,
    password   VARCHAR(255)       NOT NULL,
    created_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP
);
