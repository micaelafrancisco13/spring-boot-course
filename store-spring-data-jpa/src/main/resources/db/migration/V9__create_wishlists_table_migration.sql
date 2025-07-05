CREATE TABLE wishlists
(
    product_id UUID NOT NULL,
    user_id    UUID NOT NULL,
    CONSTRAINT pk_wishlists PRIMARY KEY (product_id, user_id)
);

ALTER TABLE wishlists
    ADD CONSTRAINT fk_wishlists_on_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE wishlists
    ADD CONSTRAINT fk_wishlists_on_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;