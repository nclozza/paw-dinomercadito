CREATE TABLE IF NOT EXISTS users (
   userId SERIAL PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10)
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
   ram VARCHAR(16),
   storage VARCHAR(16),
   operativeSystem VARCHAR(32),
   processor VARCHAR(32),
   bodySize VARCHAR(32),
   screenSize VARCHAR(32),
   screenRatio VARCHAR(32),
   rearCamera VARCHAR(128),
   frontCamera VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS posts (
   postId SERIAL PRIMARY KEY,
   productId INT REFERENCES products(productId) NOT NULL,
   userId INT REFERENCES users(userId) NOT NULL,
   price NUMERIC(8, 2) NOT NULL,
   description VARCHAR(128),
   productQuantity INT NOT NULL,
   visits INT NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
   transactionId SERIAL PRIMARY KEY,
   postId INT REFERENCES posts(postId) NOT NULL,
   buyerUserId INT REFERENCES users(userId) NOT NULL,
   productQuantity INT NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   productName VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS usersNotAuthenticated (
   userId SERIAL PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   signUpDate VARCHAR(10),
   code INT
);

CREATE TABLE IF NOT EXISTS views (
   viewId SERIAL PRIMARY KEY,
   postId INT REFERENCES posts(postId) NOT NULL,
   userId INT REFERENCES users(userId) NOT NULL
);

CREATE TABLE IF NOT EXISTS userReviews (
   userReviewId SERIAL PRIMARY KEY,
   userReviewedId INT REFERENCES users(userId) NOT NULL,
   userWhoReviewId INT REFERENCES users(userId) NOT NULL,
   rating INT NOT NULL,
   description VARCHAR(128)
);
