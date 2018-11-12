--create table body_styles(id serial primary key, name varchar(100));

--create table engine(id serial primary key, name varchar(100));

--create table transmission(id serial primary key, name varchar(100));

--create table cars(id serial primary key, name varchar(20), body_styles_id int references body_styles(id), engine_id int references engine(id), transmission_id int references transmission(id));

--insert into body_styles (name) values ('Couple');
--insert into body_styles (name) values ('Sedan');
--insert into body_styles (name) values ('Crossover');
--insert into body_styles (name) values ('Hatchback');

--insert into engine (name) values ('Diesel');
--insert into engine (name) values ('Gasoline');
--insert into engine (name) values ('Electric');
--insert into engine (name) values ('Gas / Electric hybrid');

--insert into transmission (name) values ('Manual');
--insert into transmission (name) values ('Automatic');

--insert into cars (name, body_styles_id, engine_id, transmission_id) values ('Tesla model 3', 4, 3, 2);
--insert into cars (name, body_styles_id, engine_id, transmission_id) values ('Bmw e39', 2, 1, 1);
--insert into cars (name, body_styles_id, engine_id, transmission_id) values ('ЗАЗ 965', 1, 2, 1);

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, bs.name, e.name, t.name from cars as c left outer join body_styles as bs on c.body_styles_id = bs.id left outer join engine as e on c.engine_id = e.id left outer join transmission as t on c.transmission_id = t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select bs.name from body_styles as bs left outer join cars as c on c.body_styles_id = bs.id where c.id is null;
select e.name from engine as e left outer join cars as c on e.id = c.engine_id where c.id is null;
select t.name from transmission as t left outer join cars as c on c.transmission_id = t.id where c.id is null;