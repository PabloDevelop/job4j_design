create table employee(
    id serial primary key,
    name varchar(255),
);

create table phone_numbers(
    id serial primary key,
    number int,
    employee_id int references employee(id),
);