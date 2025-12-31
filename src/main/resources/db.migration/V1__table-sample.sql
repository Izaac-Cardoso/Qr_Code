CREATE DATABASE qrcode_generator;

CREATE TABLE IF NOT EXISTS samples (

    id VARCHAR(90) NOT NULL PRIMARY KEY,
    collect_date DATE NOT NULL,
    fields JSON
);
