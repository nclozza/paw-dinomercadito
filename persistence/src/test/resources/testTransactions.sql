CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   rating NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS products (
   productID INTEGER IDENTITY PRIMARY KEY,
   productName VARCHAR(32) UNIQUE NOT NULL,
   brand VARCHAR(32),
   ram VARCHAR(8),
   storage VARCHAR(8),
   operativeSystem VARCHAR(32),
   processor VARCHAR(32),
   bodySize VARCHAR(32),
   screenSize VARCHAR(32),
   screenRatio VARCHAR(32),
   rearCamera VARCHAR(128),
   frontCamera VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS posts (
   postId INTEGER IDENTITY PRIMARY KEY,
   productPosted_productId INTEGER REFERENCES products(productId) NOT NULL,
   userSeller_userId INTEGER REFERENCES users(userId) NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   description VARCHAR(128),
   productQuantity INT NOT NULL,
   visits INT NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
   transactionId INTEGER IDENTITY PRIMARY KEY,
   postBought_postId INTEGER REFERENCES posts(postId) NOT NULL,
   buyerUser_userId INTEGER REFERENCES users(userId) NOT NULL,
   productQuantity INTEGER NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   productName VARCHAR(32) NOT NULL,
   status VARCHAR(16) NOT NULL
);


INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (300, 'dinolucas', 'dinopass', 'dinolucas@gmail.com', '1123456789', '01-01-2000');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES (2222, 'iPhone 8', 'Apple', '2GB', '256GB',
	'iOS', 'A11 Bionic', '138.4 x 67.3 x 7.3 mm', '60.9 cm2',
	'16:9', '12 MP, f/1.8, 28mm, OIS, PDAF', '7 MP, f/2.2');

INSERT INTO posts (postId, productPosted_productId, userSeller_userId, price, description, productQuantity, visits)
VALUES (700, 2222, 300, 850.00, '.', 10, 0);


INSERT INTO transactions (transactionId, postBought_postId, buyerUser_userId, productQuantity, price, productName, status)
VALUES (6000, 700, 300, 10, 800.00, 'iPhone 8', 'Confirmed');

INSERT INTO transactions (transactionId, postBought_postId, buyerUser_userId, productQuantity, price, productName, status)
VALUES (666, 700, 300, 10, 700.00, 'iPhone 8', 'Confirmed');