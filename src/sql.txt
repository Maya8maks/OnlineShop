CREATE TABLE product (
    id SERIAL,
    name varchar(255),
    price int,
    creation_date Timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (id SERIAL, email varchar(255), password varchar(255), sole varchar(255), token varchar(255));
