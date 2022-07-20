insert into users(name, roles_id) values ('Ivan', 1);
insert into role(title) values ('admin');
insert into rules(title) values ('do not sale');
insert into item(title, user_id, categor_id, states_id) values ('computer', 1, 1, 1);
insert into comments(comments, items_id) values ('fast equip', 1);
insert into attachs(numbers, items_id) values (10, 1);
insert into category(title) values ('back office');
insert into state(status) values ('deploy');