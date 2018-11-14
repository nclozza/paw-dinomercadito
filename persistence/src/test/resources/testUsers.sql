CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10)
);

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (9999, 'dinolucas', 'lucaspass', 'dinolucas@gmail.com', '1123456789', '01-01-2000');

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (1234, 'dinonico', 'nicopass', 'dinonico@gmail.com', '1123456789', '01-01-2000');

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (2345, 'dinofede', 'fedepass', 'dinofede@gmail.com', '1123456789', '01-01-2000');

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (3456, 'dinolu', 'lutaypass', 'dinolu@gmail.com', '1123456789', '01-01-2000');