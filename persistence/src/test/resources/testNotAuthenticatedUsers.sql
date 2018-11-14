CREATE TABLE IF NOT EXISTS usersNotAuthenticated (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10),
   signUpDate VARCHAR(10),
   code INT
);

INSERT INTO usersNotAuthenticated (userId, username, password, email, phone, birthdate, signUpDate, code)
VALUES (9999, 'dinolucas', 'lucaspass', 'dinolucas@gmail.com', '1123456789', '01-01-2000', '02-01-2000', 9876);

INSERT INTO usersNotAuthenticated (userId, username, password, email, phone, birthdate, signUpDate, code)
VALUES (1234, 'dinonico', 'nicopass', 'dinonico@gmail.com', '1123456789', '01-01-2000', '02-01-2000', 5432);