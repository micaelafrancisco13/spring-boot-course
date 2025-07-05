create table users
(
    id       uuid         not null
        constraint users_pk
            primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

alter table users
    owner to postgres;

create table addresses
(
    id       uuid         not null
        constraint addresses_pk
            primary key,
    street   varchar(255) not null,
    city     varchar(255) not null,
    zip_code varchar(255) not null,
    state    varchar(255) not null,
    user_id  uuid         not null
        constraint addresses_users_id_fk
            references users
);

alter table addresses
    owner to postgres;

