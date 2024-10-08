create table public.users
(
    id        serial
        primary key,
    username  varchar(50)  not null
        unique,
    name      varchar(50)  not null,
    last_name varchar(50)  not null,
    email     varchar(100) not null
        unique,
    password  varchar(255) not null,
    role      varchar
);

alter table public.users
    owner to postgres;

create table public.tasks
(
    id          serial
        primary key,
    name        varchar(100) not null,
    description text,
    user_id     integer      not null
        references public.users,
    status      varchar(20)  not null,
    start_date  timestamp    not null,
    date_limit  timestamp    not null,
    added_by_me boolean
);

alter table public.tasks
    owner to postgres;

create table public.tags
(
    id      serial
        primary key,
    name    varchar(100) not null,
    task_id integer      not null
        references public.tasks
);

alter table public.tags
    owner to postgres;

create table public.users_tasks
(
    user_id  integer not null
        constraint fknxxcd4wwmie6jtviloak5q60g
            references public.users,
    tasks_id integer not null
        constraint uk_rmt2102adpsttux8uuj10gpkk
            unique
        constraint fkqgthv7p5qsc9qdtahgfn4tkju
            references public.tasks
);

alter table public.users_tasks
    owner to postgres;

