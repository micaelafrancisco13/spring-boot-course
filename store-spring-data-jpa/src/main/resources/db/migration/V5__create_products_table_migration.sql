create table products
(
    id    uuid         not null
        constraint products_pk
            primary key,
    name  varchar(255)   not null,
    price decimal(10, 2) not null
);