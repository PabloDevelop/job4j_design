CREATE TABLE company(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'A');
insert into company(id, name) values (2, 'B');
insert into company(id, name) values (3, 'C');
insert into company(id, name) values (4, 'D');
insert into company(id, name) values (5, 'E');

insert into person(id, name, company_id) values (1, 'Ivan', 1);
insert into person(id, name, company_id) values (2, 'Petr', 1);
insert into person(id, name, company_id) values (3, 'Nik', 1);
insert into person(id, name, company_id) values (4, 'Olya', 1);
insert into person(id, name, company_id) values (5, 'Anya', 1);
insert into person(id, name, company_id) values (6, 'Igor', 2);
insert into person(id, name, company_id) values (7, 'Pavel', 2);
insert into person(id, name, company_id) values (8, 'Lena', 2);
insert into person(id, name, company_id) values (9, 'Max', 2);
insert into person(id, name, company_id) values (10, 'Alex', 3);
insert into person(id, name, company_id) values (11, 'GLeb', 3);
insert into person(id, name, company_id) values (12, 'Dima', 3);
insert into person(id, name, company_id) values (13, 'Sonya', 4);
insert into person(id, name, company_id) values (14, 'Katya', 4);
insert into person(id, name, company_id) values (15, 'Andrey', 5);

select p.name name, c.name company from person p left join company c on p.company_id = c.id where c.id != 5;

select c.name company_name, count(p.company_id) employees_count from company c inner join person p on p.company_id = c.id
group by c.name having count(p.company_id) > 1 order by c.name limit 1;