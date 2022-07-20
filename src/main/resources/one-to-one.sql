create table osobie_primeti(
    id serial primary key,
    title text
);

create table persons(
    id serial primary key,
    name varchar(255)
);

create table persons_primeti(
    id serial primary key,
    primeta_id int references osobie_primeti(id) unique,
    person_id int references persons(id) unique
);