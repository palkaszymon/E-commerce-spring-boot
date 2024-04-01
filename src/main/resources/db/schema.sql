CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(255),
    price DECIMAL(10,2) NOT NULL
);
