-- liquibase formatted sql
-- changeset yurii:add-position-in-task

ALTER TABLE tasks ADD COLUMN position int;