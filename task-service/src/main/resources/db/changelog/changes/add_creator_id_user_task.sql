-- liquibase formatted sql
-- changeset yurii:add-creator-id-user-task

ALTER TABLE tasks ADD COLUMN id_user_creator uuid;
