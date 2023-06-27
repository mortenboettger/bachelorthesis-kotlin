CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(48) PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    house_number_addition VARCHAR(10),
    post_code VARCHAR(20) NOT NULL,
    city VARCHAR(255) NOT NULL,
    district VARCHAR(255),
    phone_number VARCHAR(255),
    email_address VARCHAR(255) UNIQUE,
    INDEX address_idx (street, house_number, house_number_addition, post_code, city, district)
) ENGINE = INNODB;