create table konditerskaya(
    id serial primary key,
    title varchar(255)
);

create table goods(
    id serial primary key,
    title varchar(255),
    konditerskaya_id int references konditerskaya(id)
);

insert into konditerskaya(title) values ('U Vasi');
insert into konditerskaya(title) values ('U Leni');
insert into goods(title, konditerskaya_id) values ('khleb', 1);
insert into goods(title, konditerskaya_id) values ('bulochka', 2);

select g.title, k.title
from goods as g join konditerskaya as k on g.konditerskaya_id = k.id;

select g.title Товар, k.title Название
from goods as g join konditerskaya as k on g.konditerskaya_id = k.id;

select g.title Товар, k.title Название
from goods g join konditerskaya k on g.konditerskaya_id = k.id;