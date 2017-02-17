--liquibase formatted sql

--changeset AlexanderGaptullin:1 dbms:oracle context:legacy
create table person
(
  id   number(10),
  name varchar2(100) not null
);
--rollback drop table person;

--changeset AlexanderGaptullin:2 dbms:oracle context:legacy
alter table person
  add constraint pk_person primary key (ID);
--rollback alter table person drop constraint pk_person;

--changeset AlexanderGaptullin:3 dbms:oracle context:legacy
alter table person
  add constraint ui_person$name unique (NAME);
--rollback alter table person drop constraint ui_person$name;

--changeset AlexanderGaptullin:4 dbms:oracle context:legacy
create table timelog
(
  id              number(10),
  person_id       number(10) not null,
  log_description varchar2(200),
  start_datetime  date not null,
  end_datetime    date not null
);
--rollback drop table timelog;

--changeset AlexanderGaptullin:5 dbms:oracle context:legacy
create index i_timelog$person_id on timelog (person_id);
--rollback drop index i_timelog$person_id;

--changeset AlexanderGaptullin:6 dbms:oracle context:legacy
alter table timelog add constraint pk_timelog primary key (id);
--rollback alter table timelog drop constraint pk_timelog;

--changeset AlexanderGaptullin:7 dbms:oracle context:legacy
create sequence seq_person
minvalue 1
maxvalue 9999999999
start with 1
increment by 1;
--rollback drop sequence seq_person

--changeset AlexanderGaptullin:8 dbms:oracle context:legacy
create sequence seq_timelog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1;
--rollback drop sequence seq_timelog

--changeset AlexanderGaptullin:9 dbms:oracle context:legacy splitStatements:false endDelimiter:"/"
create or replace trigger person_b_i
  before insert
  on person
  for each row
declare
begin
  :new.id := seq_person.nextval;
end person_b_i;
/
--rollback drop trigger person_b_i;

--changeset AlexanderGaptullin:10 dbms:oracle context:legacy splitStatements:false endDelimiter:"/"
create or replace trigger timelog_b_i
  before insert
  on timelog
  for each row
declare
  -- local variables here
begin
  :new.id := seq_timelog.nextval;
end timelog_b_i;
/
--rollback drop trigger timelog_b_i