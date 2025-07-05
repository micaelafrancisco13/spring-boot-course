create table user_tags
(
    user_id uuid not null
        constraint user_tags_users_id_fk
            references users
            on delete cascade,
    tag_id  uuid not null
        constraint user_tags_tags_id_fk
            references tags
            on delete cascade,
    constraint user_tags_pk
        primary key (user_id, tag_id)
);

