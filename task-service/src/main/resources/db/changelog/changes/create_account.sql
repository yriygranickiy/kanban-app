-- liquibase formatted sql
-- changeset yurii:add-accounts
CREATE TABLE IF NOT EXISTS accounts (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          email VARCHAR(100) NOT NULL UNIQUE,
                          full_name VARCHAR(100),
                          avatar_url VARCHAR(255)
);