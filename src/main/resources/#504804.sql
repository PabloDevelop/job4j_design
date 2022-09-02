create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values ('TV', 'Sony', 100, 10000);
insert into products(name, producer, count, price) values ('Bed', 'Ikea', 150, 2500);
insert into products(name, producer, count, price) values ('Shirt', 'Zara', 200, 100);
insert into products(name, producer, count, price) values ('Suit', 'Bolshevichka', 0, 15000);

-- Функция удаляет строку по id если количество товара == 0
create or replace procedure delete_good(u_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id and count = 0;
    END;
$$;

call delete_good(4);

-- Функция удаляет строку по id если количество товара == 0
create or replace function delete_good1(u_id integer)
returns void
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id and count = 0;
    END;
$$;

select delete_good(5);