insert into person (name) values ('Nick');
insert into person (name) values ('Sara');

insert into timelog (person_id, log_description, start_datetime, end_datetime) values ((select id from person where name='Nick'), 'Nick task 1', sysdate, sysdate+1/24);
insert into timelog (person_id, log_description, start_datetime, end_datetime) values ((select id from person where name='Sara'), 'Sara task 1', sysdate, sysdate+1/24);

