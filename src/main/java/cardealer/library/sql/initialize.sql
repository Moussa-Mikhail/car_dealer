INSERT INTO genre(name)
VALUES ("science fiction"),
       ("fantasy"),
       ("romance"),
       ("historical fiction"),
       ("non-fiction");

INSERT INTO author(first_name, last_name)
VALUES ("Eoin", "Colfer"),
       ("Christopher", "Paolini"),
       ("Scott", "Card"),
       ("Fake", "Author");

INSERT INTO book(title, author_id, genre_id, isbn, quantity)
VALUES ("Artemis Fowl", 1, 1, "978-1368036986", 5),
       ("Artemis Fowl: The Arctic Incident", 1, 1, "978-0-43945070-6", 3),
       ("Eragon", 2, 2, "0-9666213-3-6", 2),
       ("Ender's Game", 3, 1, "0-312-93208-1", 3),
       ("Speaker for the Dead", 3, 1, "0-312-93738-5", 5),
       ("Fake Book 1", 4, 5, "0", 10),
       ("Fake Book 2", 4, 5, "1", 10);

INSERT INTO member(first_name, last_name, date_joined)
VALUES ("Moussa", "Mikhail", CURDATE()),
       ("John", "Doe", CURDATE()),
       ("Alice", "Smith", CURDATE()),
       ("Fake", "Person", CURDATE());

INSERT INTO fee_type(description)
VALUES ("Late"),
       ("Lost"),
       ("Damaged"),
       ("Destroyed");

INSERT INTO employee(first_name, last_name, salary, date_hired)
VALUES ("Moussa", "Mikhail", 50000, CURDATE()),
       ("Fake", "Employee", 100000, CURDATE());

INSERT INTO status(description)
VALUES ("checked out"),
       ("returned"),
       ("late"),
       ("lost"),
       ("damaged"),
       ("destroyed");

INSERT INTO checkout(member_id, book_id, employee_id, checkout_date, return_by_date)
VALUES (2, 1, 1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY)),
       (3, 4, 1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY)),
       (3, 5, 1, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY));

INSERT INTO fee(member_id, fee_type_id, amount)
VALUES (2, 1, 1.00),
       (1, 2, 100.0),
       (1, 1, 10.0);