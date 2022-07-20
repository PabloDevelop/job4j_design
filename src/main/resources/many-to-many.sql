create table cars(
    id serial primary key,
    number int
);

create table users(
    id serial primary key,
    name varchar(255)
);

create table car_sharing(
    id serial primary key,
    car_id int references cars(id),
    user_id int references users(id)
);