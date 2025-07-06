CREATE OR REPLACE FUNCTION find_products_by_price_range(
    min_price NUMERIC,
    max_price NUMERIC
)
    RETURNS SETOF products AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM products p
        WHERE p.price BETWEEN min_price AND max_price
        ORDER BY p.name;
END;
$$ LANGUAGE plpgsql;

-- How to call the function:
-- SELECT * FROM find_products_by_price_range(10.00, 100.00);