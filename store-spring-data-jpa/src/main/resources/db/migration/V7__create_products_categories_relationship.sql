alter table products
    add category_id uuid not null
        constraint products_categories_id_fk
            references categories (id)
            on delete restrict;
