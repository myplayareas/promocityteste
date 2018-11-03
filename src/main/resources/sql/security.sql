--Create the database promocity
CREATE DATABASE promocity;

--Create users table to give access to aplication
CREATE TABLE users (
  username VARCHAR(50)  NOT NULL PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  enabled  BOOLEAN      NOT NULL
)
  ENGINE = InnoDb;
CREATE TABLE authorities (
  username  VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users (username),
  UNIQUE INDEX authorities_idx_1 (username, authority)
)
  ENGINE = InnoDb;

ALTER TABLE users ADD id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    ADD INDEX (id);

ALTER TABLE users AUTO_INCREMENT=1;

--Populate promotion and coupon tables
insert promotion values(null, 'Promocao 1', CURDATE(), CURDATE()+1);
insert coupon values(null, 'Cupom 1 da promocao 1', 10, '1022112233');
insert promotion_coupons values(1,1)
insert promotion values(null, 'Promocao 2', CURDATE()+1, CURDATE()+2);
insert coupon values(null, 'Cupom 2 da promocao 2', 20, '20221122332');
insert promotion_coupons values(2,2)
