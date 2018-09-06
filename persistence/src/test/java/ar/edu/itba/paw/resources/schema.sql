CREATE TABLE IF NOT EXISTS users (
   userId INTEGER IDENTITY PRIMARY KEY,
   username VARCHAR(32) UNIQUE NOT NULL,
   password VARCHAR(32) NOT NULL,
   email VARCHAR(32),
   phone VARCHAR(16),
   addressId INT REFERENCES addresses(addressId) NOT NULL,
   birthdate DATE,
   funds NUMERIC(5, 2)
);