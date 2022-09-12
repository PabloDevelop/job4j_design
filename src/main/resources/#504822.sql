CREATE TABLE gender
(
    id     SERIAL PRIMARY KEY,
    gender varchar(8)
);

CREATE TABLE person
(
    id        SERIAL PRIMARY KEY,
    name      varchar(255),
    address   text,
    gender_id int references gender (id)
);

CREATE TABLE movies
(
    id   SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE delivery
(
    id        SERIAL PRIMARY KEY,
    movies_id integer references movies (id),
    person_id integer references person (id)
);

INSERT INTO gender (gender)
VALUES ('мужской'),
       ('женский');

INSERT INTO person (name, address, gender_id)
VALUES ('Ольга Егорова', '1-й Казанский перулок, д. 14', 1),
       ('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 1),
       ('Иванов Сергей', 'ул. Ленина, д. 7, кв. 130', 2);

INSERT INTO movies (name)
VALUES ('Пираты Карибского моря'),
       ('Матрица: Революция'),
       ('Человек, который изменил все'),
       ('Интерстеллар');

INSERT INTO delivery (person_id, movies_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 2);

SELECT *
from delivery;