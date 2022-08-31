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

-- Процедура удаляет стрку если колиество товара == 0
create or replace procedure delete_good(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count = 0 THEN
            delete from products where id = u_id;
        end if;
    END;
$$;

call delete_good(0, 1);

-- Функция удаляет строку если id товара > 0
create or replace function delete_good(u_id integer)
returns void
language 'plpgsql'
as $$
    BEGIN
        if u_id > 0 THEN
            delete from products where id = u_id;
        end if;
    END;
$$;

select delete_good(2);