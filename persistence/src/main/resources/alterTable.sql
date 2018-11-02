ALTER TABLE addresses
RENAME COLUMN userId TO userAddress_userId;

ALTER TABLE posts
RENAME COLUMN productId TO productPosted_productId;
ALTER TABLE posts
RENAME COLUMN userId TO userSeller_userId;
ALTER TABLE posts
ADD COLUMN visits INT NOT NULL DEFAULT 0;

ALTER TABLE transactions
RENAME COLUMN postId TO postBuyed_postId;
ALTER TABLE transactions
RENAME COLUMN buyerUserId TO buyerUser_userId;
ALTER TABLE transactions
ADD COLUMN status VARCHAR(16) NOT NULL DEFAULT 'Confirmed';

ALTER TABLE users
DROP COLUMN funds;
ALTER TABLE users
ADD COLUMN rating NUMERIC(10, 2);

ALTER TABLE usersnotauthenticated
DROP COLUMN funds;