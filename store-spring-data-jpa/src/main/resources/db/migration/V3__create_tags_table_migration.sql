create table tags
(
    id   uuid         not null
        constraint tags_pk
            primary key,
    name varchar(255) not null
);

alter table tags
    owner to postgres;

