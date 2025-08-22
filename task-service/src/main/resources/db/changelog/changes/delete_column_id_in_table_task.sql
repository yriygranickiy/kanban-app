-- liquibase formatted sql
-- changeset yurii:delete-board_id-in-table_column
alter table tasks drop column column_id;