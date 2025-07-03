create table tags
(
    id   bigint       not null
        constraint tags_pk
            primary key,
    name varchar(255) not null
);

alter table tags
    owner to postgres;

