DROP TABLE people IF EXISTS CASCADE;

CREATE TABLE people (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);