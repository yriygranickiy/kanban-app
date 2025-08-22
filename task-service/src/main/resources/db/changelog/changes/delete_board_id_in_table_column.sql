-- liquibase formatted sql
-- changeset yurii:delete-board_id-in-table_column

alter table columns drop column board_id
