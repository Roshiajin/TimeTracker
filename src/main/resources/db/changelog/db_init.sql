create schema `timetracker` default character set utf8;

create table if not exists `timetracker`.`person` (
  `id` int not null auto_increment,
  `name` varchar(100) not null,
  primary key (`id`) );

create table if not exists `timetracker`.`timelog` (
  `id` int not null auto_increment,
  `person_id` int not null,
  `log_description` varchar(200) null,
  `start_datetime` datetime not null,
  `end_datetime` datetime not null,
  primary key (`id`) );

insert into person (name) values ("Nick");

insert into person (name) values ("Sara");

insert into timelog (person_id, log_description, start_datetime, end_datetime) values ((select id from person where name="Nick"), "Nick task 1", sysdate(), date_add(sysdate(), interval 1 hour));

insert into timelog (person_id, log_description, start_datetime, end_datetime) values ((select id from person where name="Sara"), "Sara task 1", sysdate(), date_add(sysdate(), interval 1 hour));
