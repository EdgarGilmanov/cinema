CREATE TABLE place
(id  SERIAL PRIMARY KEY,
place INT,
status BOOLEAN);

INSERT INTO place
(id, place, status) VALUES (1, 1, true);
INSERT INTO place
(id, place, status) VALUES (2, 2, false);
INSERT INTO place
(id, place, status) VALUES (3, 3, true);
INSERT INTO place
(id, place, status) VALUES (4, 1, false);
INSERT INTO place
(id, place, status) VALUES (5, 2, true);
INSERT INTO place
(id, place, status) VALUES (6, 3, false);
INSERT INTO place
(id, place, status) VALUES (7, 1, true);
INSERT INTO place
(id, place, status) VALUES (8, 2, false);
INSERT INTO place
(id, place, status) VALUES (9, 3, false);


CREATE TABLE hall
(row INT NOT NULL,
placeId INT REFERENCES place(id));

INSERT INTO hall
(row, placeId) VALUES (1, 1);
INSERT INTO hall
(row, placeId) VALUES (1, 2);
INSERT INTO hall
(row, placeId) VALUES (1, 3);
INSERT INTO hall
(row, placeId) VALUES (2, 4);
INSERT INTO hall
(row, placeId) VALUES (2, 5);
INSERT INTO hall
(row, placeId) VALUES (2, 6);
INSERT INTO hall
(row, placeId) VALUES (3, 7);
INSERT INTO hall
(row, placeId) VALUES (3, 8);
INSERT INTO hall
(row, placeId) VALUES (3, 9);

CREATE TABLE accounts
(id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
phone TEXT NOT NULL UNIQUE,
room INT,
place INT);

INSERT INTO accounts
(name, phone) VALUES ('testName', '30405');

