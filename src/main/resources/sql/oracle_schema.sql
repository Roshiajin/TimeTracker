-- Create table
create table person
(
  id   number(10),
  name varchar2(100) not null
);

-- Create/Recreate primary, unique and foreign key constraints 
alter table person
  add constraint pk_person primary key (ID);
alter table person
  add constraint ui_person$name unique (NAME);
  
-- Create table
create table timelog
(
  id              number(10),
  person_id       number(10) not null,
  log_description varchar2(200),
  start_datetime  date not null,
  end_datetime    date not null
);

-- Create/Recreate indexes 
create index i_timelog$person_id on timelog (person_id);
-- Create/Recreate primary, unique and foreign key constraints 
alter table timelog
  add constraint pk_timelog primary key (id);
  
-- Create sequence 
create sequence seq_person
minvalue 1
maxvalue 9999999999
start with 1
increment by 1;

-- Create sequence 
create sequence seq_timelog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1;

-- Create trigger
create or replace trigger person_b_i
  before insert
  on person
  for each row
declare
  -- local variables here
begin
  :new.id := seq_person.nextval;
end person_b_i;
/

-- Create trigger
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
  
  
