ALTER TABLE member
    ADD email VARCHAR(30) NULL;

ALTER TABLE employee
    ADD email VARCHAR(30);

ALTER TABLE genre
    CHANGE COLUMN name name VARCHAR(30) NOT NULL;