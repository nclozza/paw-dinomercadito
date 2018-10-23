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
   funds NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS posts (
   postId INTEGER IDENTITY PRIMARY KEY,
   productId INTEGER REFERENCES products(productId) NOT NULL,
   userId INTEGER REFERENCES users(userId) NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   description VARCHAR(128),
   visits INT NOT NULL
);


INSERT INTO products (productid, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(99998, 'iPhone X', 'Apple', '3GB', '256GB',
	'iOS', 'A11 Bionic', '143.6 x 70.9 x 7.7 mm', '84.4 cm2',
	'19.5:9', '12 MP, f/1.8, 28mm, 1.22µm, OIS, PDAF --- 12 MP, f/2.4, 52mm, 1.0µm, OIS, PDAF, 2x optical zoom',
	'7 MP, f/2.2, 32mm');

INSERT INTO products (productid, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(6666, 'iPhone 8', 'Apple', '2GB', '256GB',
	'iOS', 'A11 Bionic', '138.4 x 67.3 x 7.3 mm', '60.9 cm2',
	'16:9', '12 MP, f/1.8, 28mm, OIS, PDAF', '7 MP, f/2.2');

INSERT INTO users (userId, username, password, email, phone, birthdate, funds)
VALUES (99997, 'dinolucas', 'dinopass', 'dinolucas@gmail.com', '1123456789', '01-01-2000', 50000.00);

INSERT INTO users (userId, username, password, email, phone, birthdate, funds)
VALUES (5555, 'dinonico', 'dinopass', 'dinonico@gmail.com', '1123456789', '01-01-2000', 50000.00);


INSERT INTO posts (postId, productId, userId, price, description, visits)
VALUES (99999, 99998, 99997, 450.00, '', 5);

INSERT INTO posts (postId, productId, userId, price, description, visits)
VALUES (99008, 99998, 5555, 150.00, '', 5);

INSERT INTO posts (postId, productId, userId, price, description, visits)
VALUES (99007, 6666, 5555, 1000.00, '', 5);

INSERT INTO posts (postId, productId, userId, price, description, visits)
VALUES (99006, 6666, 99997, 580.00, '', 5);