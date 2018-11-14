CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   rating NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS forgotPasswords (
   forgotPasswordId INTEGER IDENTITY PRIMARY KEY,
   userForgot_userId INT REFERENCES users(userId) NOT NULL,
   requestDate VARCHAR(10),
   code VARCHAR(64) NOT NULL
);


INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (8888, 'dinolucas', 'dinopass', 'dinolucas@gmail.com', '1123456789', '01-01-2000');

INSERT INTO forgotPasswords (forgotPasswordId, userForgot_userId, requestDate, code)
VALUES (9999, 8888, '08-08-2008', 'code');