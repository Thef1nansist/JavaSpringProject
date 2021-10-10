CREATE TABLE verification_token(
id INT NOT NULL auto_increment PRIMARY KEY,
user_id BIGINT,
token VARCHAR(255),
expiry_date TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id)
)ENGINE=INNODB;