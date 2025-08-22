-- liquibase formatted sql
-- changeset yurii:create-table-board-columns

create table board_columns(
    id uuid primary key default gen_random_uuid(),
    board_id uuid not null ,
    column_id uuid not null,
    position int not null,

    constraint fk_board foreign key (board_id) references boards (id) on delete cascade,
    constraint fk_column foreign key (column_id) references columns (id) on delete cascade
)

