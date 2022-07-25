create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Coupe'), ('Hatchback'), ('Roadster'), ('Roadster'), ('Sedan'), ('Minivan');
insert into car_engines(name) values ('Straight'), ('Inline'), ('V'), ('V'), ('V'), ('Flat');
insert into car_transmissions(name) values ('Manual'), ('Manual'), ('Manual'), ('Automatic'),('Automatic'),
('Automatic'), ('CVT'), ('RGD');
insert into cars(name, body_id, engine_id, transmission_id) values ('Mersedess', 5, 3, 4), ('Opel', 2, 1, 1),
('BMW', 3, 4, 5), ('AUDI', 4, 6, 8);

select * from cars;
select b.name from car_bodies b left join cars c on c.body_id = b.id where c.id is null;
select e.name from car_engines e left join cars c on c.body_id = e.id where c.id is null;
select t.name from car_transmissions t left join cars c on c.body_id = t.id where c.id is null;