-- liquibase formatted sql
-- changeset yurii:add_field_board_id_in_column_task_table

alter table column_tasks add column board_id uuid;
