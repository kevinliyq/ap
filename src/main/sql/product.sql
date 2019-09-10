CREATE DATABASE IF NOT EXISTS d_product;

CREATE TABLE IF NOT EXISTS t_product(
	id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	product_name varchar(255) not null,
	partner varchar(255) not null,
	price NUMERIC,
	sellable int,
	cancellationPolicies  varchar(255)
);