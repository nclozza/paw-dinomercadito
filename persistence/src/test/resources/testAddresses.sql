CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(60) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   birthdate VARCHAR(10)
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

INSERT INTO users (userId, username, password, email, phone, birthdate)
VALUES (9999, 'UsernameAddress', 'Password', 'mailAddress@gmail.com', '1123456789', '01-01-2000');

INSERT INTO addresses (addressId, userAddress_userId, street, number, city, province, zipCode, country)
VALUES (8888, 9999, 'Siempreviva', 1234, 'Springfield', 'pvcia', '1234', 'Arg');