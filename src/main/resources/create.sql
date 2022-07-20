create table tasks(
    id serial primary key,
    title varchar(255),
    task text,
    deadline timestamptz
);

insert into tasks(title, task, deadline) values('Провести совещание',
                                               'О космическом мусоре',
                                               TIMESTAMP WITH TIME ZONE '2022-07-3 10:00:00+03');

update tasks set deadline = TIMESTAMP WITH TIME ZONE '2022-07-30 16:00:00+03';

delete from tasks;