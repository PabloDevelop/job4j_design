create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

create table teens(
    name varchar(255),
    gender varchar(255)
)

insert into departments(name)
values ('IT'), ('Finance'), ('Logistic'), ('Advisory');
insert into employees(name, department_id)
values ('Vasya', 1), ('Vanya', 2), ('Olya', 3), ('Petya');
insert into teens(name, gender) values ('Vanya', 'm'), ('Vasya', 'm'), ('Petya', 'm'),
('Olya', 'f'), ('Masha', 'f'), ('Tanya', 'f');

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;
select * from departments d left join employees e on e.department_id = d.id where e.id is null;
select e.name, d.name from employees e left join departments d on e.department_id = d.id;
select e.name, d.name from departments d right join employees e on e.department_id = d.id;
select n1.name as male,
n2.name as female, (
n1.name, n2.name) as "couple"
from teens n1
cross join teens n2
where n1.gender='m' and n2.gender='f';