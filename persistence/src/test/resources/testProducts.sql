CREATE TABLE IF NOT EXISTS products (
   productId INTEGER IDENTITY PRIMARY KEY,
   productName VARCHAR(32) UNIQUE NOT NULL,
   brand VARCHAR(32),
   ram VARCHAR(8),
   storage VARCHAR(8),
   operativeSystem VARCHAR(32),
   processor VARCHAR(32),
   bodySize VARCHAR(32),
   screenSize VARCHAR(8),
   screenRatio VARCHAR(8),
   rearCamera VARCHAR(128),
   frontCamera VARCHAR(64)
);

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9999, 'iPhone XI', 'Apple', '3GB', '256GB',
	'iOS', 'A11 Bionic', '143.6 x 70.9 x 7.7 mm', '84.4 cm2',
	'19.5:9', '12 MP, f/1.8, 28mm, 1.22µm, OIS, PDAF --- 12 MP, f/2.4, 52mm, 1.0µm, OIS, PDAF, 2x optical zoom',
	'7 MP, f/2.2, 32mm');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9998, 'iPhone 8', 'Apple', '2GB', '256GB',
	'iOS', 'A11 Bionic', '138.4 x 67.3 x 7.3 mm', '60.9 cm2',
	'16:9', '12 MP, f/1.8, 28mm, OIS, PDAF', '7 MP, f/2.2');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9997, 'iPhone 8 Plus', 'Apple', '3GB', '256GB',
	'iOS', 'A11 Bionic', '158.4 x 78.1 x 7.5 mm', '83.4 cm2', '16:9',
	'12 MP, f/1.8, 28mm, OIS, PDAF ---
	12 MP, f/2.8, 57mm, 2x optical zoom, PDAF', '7 MP, f/2.2, 32mm');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9996, 'OnePlus 6', 'OnePlus', '8GB', '128GB',
	'Android', 'Qualcomm Snapdragon 845',
	'155.7 x 75.4 x 7.8 mm', '98.4 cm2', '19:9',
	'16 MP, f/1.7, 25mm, 1/2.6", 1.22µm, OIS, PDAF ---
	20 MP (16 MP effective), f/1.7, 25mm, 1/2.8", 1.0µm, PDAF',
	'16 MP, f/2.0, 25mm, 1/3", 1.0µm');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9995, 'Galaxy S8', 'Samsung', '4GB', '64GB',
	'Android', 'Qualcomm Snapdragon 835',
	'148.9 x 68.1 x 8 mm', '84.8 cm2', '18.5:9',
	'12 MP, f/1.7, 26mm, 1/2.5", 1.4µm, OIS, dual pixel PDAF',
	'8 MP, f/1.7, 25mm, 1/3.6", 1.22µm, AF');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9994, 'Galaxy S8+', 'Samsung', '6GB', '128GB',
	'Android', 'Qualcomm Snapdragon 835',
	'159.5 x 73.4 x 8.1 mm', '98.3 cm2', '18.5:9',
	'12 MP, f/1.7, 26mm, 1/2.5", 1.4µm, OIS, dual pixel PDAF',
	'8 MP, f/1.7, 25mm, 1/3.6", 1.22µm, AF');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9993, 'Galaxy S9', 'Samsung', '4GB', '256GB',
	'Android', 'Qualcomm Snapdragon 845', '147.7 x 68.7 x 8.5 mm',
	'84.8 cm2', '18.5:9',
	'12 MP, f/1.5-2.4, 26mm, 1/2.5", 1.4µm, OIS, dual pixel PDAF',
	'8 MP, f/1.7, 25mm, 1/3.6", 1.22µm, AF');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9992, 'Galaxy S9+', 'Samsung', '6GB', '256GB',
	'Android', 'Qualcomm Snapdragon 845', '158.1 x 73.8 x 8.5 mm',
	'98.3 cm2', '18.5:9',
	'12 MP, f/1.5-2.4, 26mm, 1/2.55", 1.4µm, Dual Pixel PDAF, OIS ---
	12 MP, f/2.4, 52mm, 1/3.6", 1µm, AF, OIS, 2x optical zoom',
	'8 MP, f/1.7, 25mm, 1/3.6", 1.22µm, AF');

INSERT INTO products (productId, productname, brand, ram, storage, operativesystem, processor, bodysize, screensize, screenratio, rearcamera, frontcamera)
VALUES(9991, 'Pocophone F1', 'Xiaomi', '8GB', '256GB',
	'Android', 'Qualcomm Snapdragon 845', '155.5 x 75.3 x 8.8 mm',
	'96.2 cm2', '18.7:9', '12 MP, f/1.9, 1/2.55", 1.4µm, dual pixel PDAF ---
	5 MP, f/2.0, 1.12µm, depth sensor', '20 MP, f/2.0, 0.9µm');
