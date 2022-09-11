CREATE TABLE person
(
    id     SERIAL PRIMARY KEY,
    name   varchar(255),
    gender varchar(8)
);

CREATE TABLE address
(
    id      SERIAL PRIMARY KEY,
    address text
);

CREATE TABLE movies
(
    id   SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE delivery
(
    id         SERIAL PRIMARY KEY,
    movies_id  integer references movies (id),
    person_id  integer references person (id),
    address_id integer references address (id)
);

INSERT INTO person (name, gender)
VALUES ('Ольга Егорова', 'женский'),
       ('Иванов Сергей', 'мужской');

INSERT INTO address (address)
VALUES ('1-й Казанский перулок, д. 14'),
       ('ул. Центральная, д. 40, кв. 74'),
       ('ул. Ленина, д. 7, кв. 130');

INSERT INTO movies (name)
VALUES ('Пираты Карибского моря'),
       ('Матрица: Революция'),
       ('Человек, который изменил все'),
       ('Интерстеллар');

INSERT INTO delivery (movies_id, person_id, address_id)
VALUES (1, 1, 1),
       (2, 1, 1),
       (3, 2, 2),
       (4, 2, 2),
       (2, 2, 3);

SELECT * from delivery;