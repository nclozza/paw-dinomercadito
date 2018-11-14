CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   rating NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS userReviews (
   userReviewId INTEGER IDENTITY PRIMARY KEY,
   reviewedUser_userId INT REFERENCES users(userId) NOT NULL,
   reviewer_userId INT REFERENCES users(userId) NOT NULL,
   rating INT NOT NULL,
   description VARCHAR(128)
);

INSERT INTO users (userId, username, password, email, phone, birthdate, rating)
VALUES (7777, 'dinolucas', 'dinopass', 'dinolucas@gmail.com', '1123456789', '01-01-2000', 8.00);

INSERT INTO users (userId, username, password, email, phone, birthdate, rating)
VALUES (8888, 'dinonico', 'dinopass', 'dinonico@gmail.com', '1123456789', '01-01-2000', 6.00);

INSERT INTO userReviews (userReviewId, reviewedUser_userId, reviewer_userId, rating, description)
VALUES (9999, 8888, 7777, 4.00, 'It was okay');

INSERT INTO userReviews (userReviewId, reviewedUser_userId, reviewer_userId, rating, description)
VALUES (9998, 7777, 8888, 7.00, 'It was pretty good');
