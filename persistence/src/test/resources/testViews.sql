CREATE TABLE IF NOT EXISTS products (
   productId INTEGER IDENTITY PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   rating NUMERIC(10, 2)
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

CREATE TABLE IF NOT EXISTS views (
   viewId INTEGER IDENTITY PRIMARY KEY,
   postVisited_postId INT REFERENCES posts(postId) NOT NULL,
   userWhoVisited_userId INT REFERENCES users(userId) NOT NULL
);


INSERT INTO products (productid, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9876, 'iPhone X', 'Apple', '3GB', '256GB',
	'iOS', 'A11 Bionic', '143.6 x 70.9 x 7.7 mm', '84.4 cm2',
	'19.5:9', '12 MP, f/1.8, 28mm, 1.22µm, OIS, PDAF --- 12 MP, f/2.4, 52mm, 1.0µm, OIS, PDAF, 2x optical zoom',
	'7 MP, f/2.2, 32mm');

INSERT INTO products (productid, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(4567, 'iPhone 8', 'Apple', '2GB', '256GB',
	'iOS', 'A11 Bionic', '138.4 x 67.3 x 7.3 mm', '60.9 cm2',
	'16:9', '12 MP, f/1.8, 28mm, OIS, PDAF', '7 MP, f/2.2');

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (6666, 'dinolucas', 'dinopass', 'dinolucas@gmail.com', '1123456789', '01-01-2000');

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (12345, 'dinonico', 'dinopass', 'dinonico@gmail.com', '1123456789', '01-01-2000');

INSERT INTO posts (postId, productPosted_productId, userSeller_userId, price, description, productQuantity, visits)
VALUES (5555, 9876, 6666, 450.00, '', 5, 2);

INSERT INTO posts (postId, productPosted_productId, userSeller_userId, price, description, productQuantity, visits)
VALUES (45678, 4567, 12345, 150.00, '', 5, 2);

INSERT INTO views (viewId, postVisited_postId, userWhoVisited_userId)
VALUES (7777, 5555, 6666);
