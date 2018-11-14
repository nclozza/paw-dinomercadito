CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   funds NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS addresses (
   addressId INTEGER IDENTITY PRIMARY KEY,
   userId INT REFERENCES users(userId) NOT NULL,
   street VARCHAR(32) NOT NULL,
   number INT NOT NULL,
   city VARCHAR(32),
   province VARCHAR(32),
   zipCode VARCHAR(16) NOT NULL,
   country VARCHAR(32)
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
   productId INTEGER REFERENCES products(productId) NOT NULL,
   userId INT REFERENCES users(userId) NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   description VARCHAR(128),
   visits INT NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
   transactionId INTEGER IDENTITY PRIMARY KEY,
   postId INT REFERENCES posts(postId) NOT NULL,
   buyerUserId INT REFERENCES users(userId) NOT NULL,
   productQuantity INT NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   productName VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS usersNotAuthenticated (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   funds NUMERIC(10, 2),
   signUpDate VARCHAR(10),
   code INT
);


CREATE TABLE IF NOT EXISTS views (
   viewId INTEGER IDENTITY PRIMARY KEY,
   postVisited_postId INT REFERENCES posts(postId) NOT NULL,
   userWhoVisited_userId INT REFERENCES users(userId) NOT NULL
);

CREATE TABLE IF NOT EXISTS userReviews (
   userReviewId INTEGER IDENTITY PRIMARY KEY,
   userReviewed_userId INT REFERENCES users(userId) NOT NULL,
   userWhoReview_userId INT REFERENCES users(userId) NOT NULL,
   rating INT NOT NULL,
   description VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS questions (
   questionId INTEGER IDENTITY PRIMARY KEY,
   postAsked_postId INT REFERENCES posts(postId) NOT NULL,
   userWhoAsk_userId INT REFERENCES users(userId) NOT NULL,
   question VARCHAR(128) NOT NULL,
   answer VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS forgotPasswords (
  forgotPasswordId INTEGER IDENTITY PRIMARY KEY,
   userForgot_userId INT REFERENCES users(userId) NOT NULL,
   requestDate VARCHAR(10),
   code VARCHAR(64) NOT NULL
);
