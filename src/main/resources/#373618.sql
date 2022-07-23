create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Phone', 1000), ('Computer', 5500), ('Headphones', 100);
insert into people(name) values ('Vasya'), ('Petya'), ('Sasha');
insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (3, 1);

select avg(price) from devices;

select p.name, avg(dev.price)
from people p
join devices_people dp on dp.people_id=p.id
join devices dev on dp.device_id=dev.id
group by p.name;

select p.name, avg(dev.price)
from people p
join devices_people dp on dp.people_id=p.id
join devices dev on dp.device_id=dev.id
group by p.name
having avg(dev.price) > 100;