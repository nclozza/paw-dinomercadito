CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   funds NUMERIC(10, 2)
);

INSERT INTO users (userId, username, password, email, phone, birthdate, funds)
VALUES (9999, 'dinolucas', 'lucaspass', 'dinolucas@gmail.com', '1123456789', '01-01-2000', 5000.00);

INSERT INTO users (username, password, email, phone, birthdate, funds)
VALUES ('dinonico', 'nicopass', 'dinonico@gmail.com', '1123456789', '01-01-2000', 5000.00);

INSERT INTO users (username, password, email, phone, birthdate, funds)
VALUES ('dinofede', 'fedepass', 'dinofede@gmail.com', '1123456789', '01-01-2000', 5000.00);

INSERT INTO users (username, password, email, phone, birthdate, funds)
VALUES ('dinolu', 'lutaypass', 'dinolu@gmail.com', '1123456789', '01-01-2000', 5000.00);