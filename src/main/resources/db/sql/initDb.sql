CREATE TABLE customer (
	id 				INTEGER AUTO_INCREMENT PRIMARY KEY,
	name 			VARCHAR(255) NOT NULL,
	address1 		VARCHAR(50),
	address2 		VARCHAR(50),
	city 			VARCHAR(50),
	state 			VARCHAR(20),
	zip				INTEGER(5)
);