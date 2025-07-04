CREATE TABLE wishlists
(
    product_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_user_wishlist PRIMARY KEY (product_id, user_id)
);

ALTER TABLE wishlists
    ADD CONSTRAINT fk_usewis_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE wishlists
    ADD CONSTRAINT fk_usewis_on_user FOREIGN KEY (user_id) REFERENCES users (id);