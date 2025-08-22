-- liquibase formatted sql
-- changeset yurii:create-table-column-tasks

create table column_tasks(
    id uuid primary key default gen_random_uuid(),
    id_column uuid not null ,
    id_task uuid not null ,
    position int not null,

    constraint fk_column foreign key (id_column) references columns(id) on delete cascade,
    constraint fk_task foreign key (id_task) references tasks(id) on delete cascade
)
