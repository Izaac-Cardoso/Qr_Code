CREATE DATABASE qrcode_generator;

CREATE TABLE samples (

    id VARCHAR(90) NOT NULL PRIMARY KEY,
    collect_date DATE NOT NULL,
    fields JSON
);
