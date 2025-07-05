CREATE TABLE user_wishlist
(
    product_id UUID NOT NULL,
    user_id    UUID NOT NULL,
    CONSTRAINT pk_user_wishlist PRIMARY KEY (product_id, user_id)
);

ALTER TABLE user_wishlist
    ADD CONSTRAINT fk_usewis_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE user_wishlist
    ADD CONSTRAINT fk_usewis_on_user FOREIGN KEY (user_id) REFERENCES users (id);