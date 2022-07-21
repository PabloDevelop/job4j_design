create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('line', 20, '1999-01-08');
insert into fauna(name, avg_age, discovery_date) values ('fish', 1, '1700-01-08');
insert into fauna(name, avg_age, discovery_date) values ('bird', 10, '1800-01-08');

select * from fauna where name like '%fish%';
select * from fauna where avg_age < 21000 and avg_age > 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';