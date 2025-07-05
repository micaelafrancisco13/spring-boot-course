create table profiles
(
    id             uuid not null
        constraint profiles_pk
            primary key
        constraint profiles_users_id_fk
            references users
            on delete cascade,
    bio            varchar(255),
    phone_number   varchar(15),
    date_of_birth  date,
    loyalty_points integer
);

alter table profiles
    owner to postgres;

