create table categories
(
    id   bigint       not null
        constraint categories_pk
            primary key,
    name varchar(255) not null
);
