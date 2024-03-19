CREATE TABLE IF NOT EXISTS tokens
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    token      VARCHAR(255) NOT NULL,
    type       VARCHAR(10)  NOT NULL,
    user_id    BIGINT       NOT NULL,
    revoked    BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
