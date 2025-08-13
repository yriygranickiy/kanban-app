-- liquibase formatted sql
-- changeset yurii:delete-position-in-task-table
alter table tasks drop column position;

