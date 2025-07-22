-- liquibase formatted sql

-- changeset author:granitskiy

-- changeSetId:02-create-roles-table
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50)  NOT NULL
);