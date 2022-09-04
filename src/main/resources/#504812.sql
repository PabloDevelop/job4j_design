create table phones (
    id serial primary key,
    model varchar(50),
    producer varchar(50),
    count integer
);

insert into phones (model, producer, count) values ('Iphone 11', 'Apple', 50);
insert into phones (model, producer, count) values ('Iphone 12', 'Apple', 30);
insert into phones (model, producer, count) values ('Iphone 13', 'Apple', 40);
insert into phones (model, producer, count) values ('Iphone 14', 'Apple', 10);
insert into phones (model, producer, count) values ('Xiaomi Mi 13', 'Apple', 55);
insert into phones (model, producer, count) values ('Xiaomi Mi 12', 'Apple', 45);
insert into phones (model, producer, count) values ('Xiaomi Mi 11', 'Apple', 35);

select avg(count) from phones;
update phones set count = 30 where model = 'Iphone 11';

select avg(count) from phones;
update phones set count = 100 where model = 'Iphone 12';
