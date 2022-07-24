create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    price float,
    expired_date date,
    type_id int references type(id)
);

insert into type(name) values
('СЫР'),
('Молоко');
insert into product(name, price, expired_date, type_id) values
('Сыр плавленный', 100, '2022-08-08', 1),
('Сыр моцарелла', 200, '2022-08-20', 1),
('Сыр гауда', 150, '2022-06-22', 1),
('Молоко 3,2 %', 100, '2022-10-10', 2),
('Кефир 3,2 %', 100, '2022-10-09', 2),
('Мороженое пломбир в стаканчике', 89, '2022-08-16', 2),
('Мороженое лакомка', 110, '2022-08-15', 2);


select * from product
join type tp on type_id=tp.id
where tp.name like 'СЫР';

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < current_timestamp;

select * from product
where price = (select max(price) from product);

select tp.name, count(*)
from product p
join type tp on type_id=tp.id
group by tp.name;

select * from product
join type tp on type_id=tp.id
where tp.name like 'СЫР' or tp. name like 'Молоко';

select tp.name
from product p
join type tp on p.type_id=tp.id
group by tp.name
having count(*) < 10;

select *
from product p
join type tp on type_id=tp.id;
