-- liquibase formatted sql
-- changeset yurii:delete-position-in-column-table

alter table columns drop column position;