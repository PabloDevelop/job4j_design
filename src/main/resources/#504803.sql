create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

-- Функция добавляет налог к товару
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Функция удаляет налог из товара
create or replace function tax1()
    returns trigger as
$$
    BEGIN
        new.price = new.price * 0.8;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Функция вставляет в таблицу данные добавленного товара
create or replace function history_insert()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        select products.name, products.price, now()
        from products;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Скрипт после вставки, уровень statement
create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute function tax();

-- Скрипт перед вставкой, уровень row
create trigger tax1_trigger
    before insert
    on products
    for each row
    execute function tax1();

-- Скрипт после вставки, уровень row
create trigger history_trigger
    after insert
    on products
    for each row
    execute function history_insert();