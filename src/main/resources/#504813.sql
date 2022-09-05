create table phones (
    id serial primary key,
    model varchar(50),
    producer varchar(50),
    count integer
);

insert into phones (model, producer, count) values ('Iphone 11', 'Apple', 50);
insert into phones (model, producer, count) values ('Iphone 12', 'Apple', 30);

begin transaction;
insert into phones (model, producer, count) values ('Xiaomi Mi 10', 'Xiaomi', 135);
savepoint first_savepoint;
select * from phones;
update phones set count = 125 where model = 'Xiaomi Mi 10';
savepoint second_savepoint;
select * from phones;
delete from phones where model = 'Iphone 11';
select * from phones;
rollback to second_savepoint;
commit;
select * from phones;