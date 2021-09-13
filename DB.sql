begin;

--drop schema if exists "people" cascade;

create schema "people";

CREATE TABLE people.people
( 
	id_person			serial,
	last_name		character varying(50) not null,
	first_name		character varying(50) not null,
	age			integer,
	email 			character varying(50) not null,
	PRIMARY KEY (id_person) 
);
------------------------------------------------------------------------------------------

INSERT INTO people.people (last_name, first_name, age, email) 
VALUES ('Sofronv', 'Ivan', 21, 'ivanse99@yandex.ru');

end;