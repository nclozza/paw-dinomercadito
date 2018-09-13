CREATE TABLE IF NOT EXISTS users (
   userId SERIAL PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(32) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   funds NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS addresses (
   addressId SERIAL PRIMARY KEY,
   userId INT REFERENCES users(userId) NOT NULL,
   street VARCHAR(32) NOT NULL,
   number INT NOT NULL,
   city VARCHAR(32),
   province VARCHAR(32),
   zipCode VARCHAR(16) NOT NULL,
   country VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS products (
   productID SERIAL PRIMARY KEY,
   productName VARCHAR(32) UNIQUE NOT NULL,
   brand VARCHAR(32),
   ram VARCHAR(8),
   storage VARCHAR(8),
   operativeSystem VARCHAR(32),
   processor VARCHAR(32),
   bodySize VARCHAR(8),
   screenSize VARCHAR(8),
   screenRatio VARCHAR(8),
   rearCamera VARCHAR(16),
   frontCamera VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS posts (
   postId SERIAL PRIMARY KEY,
   productId INT REFERENCES products(productId) NOT NULL,
   userId INT REFERENCES users(userId) NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   description VARCHAR(128),
   productQuantity INT NOT NULL
);