CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS admins (
   adminId INTEGER IDENTITY PRIMARY KEY,
   userAdmin_userId INT REFERENCES users(userId) NOT NULL
);

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (9999, 'dinolucas', 'lucaspass', 'dinolucas@gmail.com', '1123456789', '01-01-2000');

INSERT INTO admins (adminId, userAdmin_userId)
VALUES (8888, 9999)